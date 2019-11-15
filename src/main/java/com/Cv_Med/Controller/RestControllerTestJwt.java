package com.Cv_Med.Controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.Cv_Med.Hibernet.Cv;
import com.Cv_Med.JWT.GetToken;
import com.Cv_Med.JWT.JwtAuthenticationFilter;
import com.Cv_Med.JWT.JwtProperties;
import com.Cv_Med.JsonClasses.UserJson;
import com.Cv_Med.JsonClasses.UserLogin;
import com.Cv_Med.JsonClasses.UserTestJson;
import com.auth0.jwt.interfaces.Header;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RestControllerTestJwt {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private GetToken getToken;

	@PostMapping(value="/isConnectedjwt",consumes={MediaType.APPLICATION_JSON_VALUE},headers= {"Content-Type=application/json"})
	public Boolean jwtConected(HttpServletRequest request,@RequestBody Cv cv) {
		System.out.println("is connectedjwt");
		/*
		 * if(user!=null) { System.out.println("user: " +user.getUsername()); }else {
		 * System.out.println("user null"); }
		 */
		System.out.println("Autorization " + request.getHeader("Authorization"));
		System.out.println("content type " + request.getHeader("Content-Type"));
		System.out.println("Autorizationss " + request.getHeaders("*").getClass());
		System.out.println("title " + request.getParameter("title"));
		System.out.println("title cv is" +cv.getTitle());

		// System.out.println("heloo "+auth);
		System.out.println("helo msr boushaba you are authenticated maybe");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken)) {

			/* The user is logged in :) */
			System.out.println("conected");
			System.out.println("Name of user " + auth.getName());

		} else {
			System.out.println("Noconected");
		}

		return true;
	}

	@PostMapping("/testjwtauth")
	public void heloo() {

		System.out.println("hello");
		// System.out.println("my name is "+user.getUsername());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken)) {

			/* The user is logged in :) */
			System.out.println("conected");
			System.out.println("Name " + auth.getName());

		} else {
			System.out.println("Noconected");
		}

	}

	@PostMapping("/LoginAngular")
	public Boolean LoginAngular(HttpServletRequest request, @RequestBody UserJson user) {
		boolean isConected = false;
		System.out.println("Login Auto Angular");

		String username = user.getUsername();
		String password = user.getPassword();

		System.out.println(user.getPassword() + " " + user.getUsername());
		isConected = loginauto(request, username, password);
		System.out.println(isConected);
		return isConected;
	}

	@PostMapping("/isConnected")
	public Boolean isConected(HttpServletRequest request, @RequestBody UserJson user) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {

			/* The user is logged in :) */
			System.out.println(auth.getName());
			return true;

		} else {
			System.out.println("false");
			return false;
		}
	}

	@PostMapping("/registrejwt")
	public void registre() {
		System.out.println("registre");

	}

	@PostMapping("/verfiysessiontest")
	public void versession(HttpServletRequest request) {
		HttpSession session=request.getSession();
		System.out.println("username from session is "+session.getAttribute("UserName"));
		System.out.println("creation done of "+session.getAttribute("CvTitle"));
	}
		
	

	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}

	private boolean loginauto(HttpServletRequest request, String username, String password) {
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

		// Authenticate the user
		Authentication authentication = authenticationManager.authenticate(authRequest);
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);

		// Create a new session and add the security context.
		HttpSession session = ((HttpServletRequest) request).getSession(true);
		session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
		System.out.println("autoLogin");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {

			/* The user is logged in :) */
			System.out.println(auth.getName() + "!!!!!!!!!!");
			return true;

		} else {
			return false;
		}

	}

}
