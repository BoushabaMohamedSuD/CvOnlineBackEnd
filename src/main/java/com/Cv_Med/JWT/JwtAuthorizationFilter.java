package com.Cv_Med.JWT;

import com.Cv_Med.Configue.UserPrincipal;
import com.Cv_Med.Hibernet.UserDaoImplimentation;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
	private UserDaoImplimentation userRepository;

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserDaoImplimentation userRepository) {
		super(authenticationManager);
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Read the Authorization header, where the JWT token should be
		System.out.println("--------------begun authorization------------------");
		System.out.println("autorization "+request.getHeader("Authorization"));
		System.out.println("ErroHeader "+request.getHeader("Error"));
		String header = request.getHeader(JwtProperties.HEADER_STRING);
		System.out.println("header: " + header);

		// If header does not contain BEARER or is null delegate to Spring impl and exit
		if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			System.out.println("header is wrong");
			return;
		} else {
			System.out.println("header is right");
		}

		// If header is present, try grab user principal from database and perform
		// authorization
		Authentication authentication = getUsernamePasswordAuthentication(request);
		if (authentication == null) {
			System.out.println("token is not knowen from the servor");
		} else {
			System.out.println("authentication fetched");
		}

		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Continue filter execution
		chain.doFilter(request, response);
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			System.out.println("auth valide");
			
		} else {
			System.out.println("Error ethentication");
		}
		System.out.println("--------------end authorization------------------");
	}

	private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
		String token = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, "");

		if (token != null) {
			// parse the token and validate it

			//String userName = JWT.require(HMAC512(JwtProperties.SECRET.getBytes())).build().verify(token).getSubject();
			
			Claims claims = Jwts.parser()
					.setSigningKey(JwtProperties.SECRET.getBytes())
					.parseClaimsJws(token)
					.getBody();
			
			String userName = claims.getSubject();
			System.out.println("username from toke: "+userName);

			// Search in the DB if we find the user by token subject (username)
			// If so, then grab user details and create spring auth token using username,
			// pass, authorities/roles
			if (userName != null) {
				com.Cv_Med.Hibernet.User user = userRepository.show_user(userName);
				UserPrincipal principal = new UserPrincipal(user);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userName, null,
						principal.getAuthorities());
				System.out.println("auth has been returned");

				return auth;
			} else {
				System.out.println("we can't get the user from the token ");
			}
			return null;
		} else {
			System.out.println("token is not defined form header");
		}

		return null;
	}
}
