package com.Cv_Med.JWT;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Cv_Med.Bean.ErrorCatching;
import com.Cv_Med.Configue.ConfigueBean;
import com.Cv_Med.Configue.Confique_Security;
import com.Cv_Med.Configue.UserPrincipal;
import com.Cv_Med.Hibernet.User;
import com.Cv_Med.Hibernet.UserDaoImplimentation;
import com.Cv_Med.JsonClasses.UserLogin;
import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.fastinfoset.util.CharArray;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private BCryptPasswordEncoder passwordencoder;
	private AuthenticationManager authenticationManager;
	private String Error;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	/*
	 * Trigger when we issue POST request to /login We also need to pass in
	 * {"username":"dan", "password":"dan123"} in the request body
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// intantite Beans
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigueBean.class);
		ErrorCatching er = context.getBean("errorCatching", ErrorCatching.class);
		UserDaoImplimentation userDao = context.getBean("userDao", UserDaoImplimentation.class);
		userDao.IamHer();
		er.IamHer();

		// for password encoding

		// ADD a filtre error in the heade
		Error = er.getErrot();
		Error = "";
		response.addHeader("Error", Error);
		System.out.println(Error);
		// Grab credentials and map them to login viewmodel

		System.out.println("jwt authentication activited");
		UserLogin credentials = null;
		try {
			credentials = new ObjectMapper().readValue(request.getInputStream(), UserLogin.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(credentials==null) {
			System.out.println("cradentials null probleme");
		}

		// Create login token
		System.out.println("Username " + credentials.getUsername());
		System.out.println("Password " + credentials.getPassword());
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				credentials.getUsername(), credentials.getPassword(), new ArrayList<>());

		// Authenticate user
		System.out.println("begun authentication");
		User userTest = userDao.show_user(credentials.getUsername());
		if (userTest == null) {
			Error = "usernameWrong";
			response.setHeader("Error", Error);

			System.out.println(Error);

		} else {
			System.out.println("mybe password is incorrect if you are not connected");
			Error = "passwordWrong";
			response.setHeader("Error", Error);
		}

		/*
		 * else if (!userTest.getPassword().equals(credentials.getPassword())) {
		 * System.out.println("user password "+userTest.getPassword()); String csd="";
		 * csd= String.valueOf(passwordencoder.encode(credentials.getPassword()));
		 * if(csd==null) { System.out.println("cardentile password "+csd); }else {
		 * System.out.print("csd null"); }
		 * 
		 * Error = "passwordWrong"; response.setHeader("Error", Error);
		 * System.out.println(Error); }
		 */
		Authentication auth = authenticationManager.authenticate(authenticationToken);
		System.out.println("auth : " + auth);
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			System.out.println("auth valide");
			Error = "";
		} else {
			System.out.println("Error ethentication");
			Error = "Error";
		}
		System.out.println("end authetication");
		return auth;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// Grab principal
		System.out.println("authetication succes");
		UserPrincipal principal = (UserPrincipal) authResult.getPrincipal();

		// Create JWT Token
		String token = JWT.create().withSubject(principal.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
				.sign(HMAC512(JwtProperties.SECRET.getBytes()));

		// Add token in response
		if (token != null) {

			System.out.println("creation the token " + token);
			// save the user id in the session
			HttpSession session = request.getSession();
			System.out.println("clear all the session");
			session.invalidate();
			System.out.println("create a new session the session");
			HttpSession newSession = request.getSession();
			newSession.setAttribute("UserName",authResult.getName() );
			System.out.println("save the user name in the session "+newSession.getAttribute("UserName"));

		} else {
			System.out.println("token is null");
		}

		response.setHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + token);
		// response.setHeader("Boushaba",token);
		System.out.println("add it in the response");
	}

}
