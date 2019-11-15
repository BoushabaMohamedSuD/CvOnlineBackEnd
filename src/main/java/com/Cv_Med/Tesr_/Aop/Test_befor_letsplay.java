package com.Cv_Med.Tesr_.Aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.Cv_Med.JsonClasses.UserJson;

@Aspect
@Component
public class Test_befor_letsplay {
	/*@Autowired(required = false)
	private HttpServletRequest request;*/
	@Autowired
	AuthenticationManager authenticationManager;

	private int i = 0;

	@Before("execution(public void letsplay())")
	public void prepare_toplay() {
		System.out.println(
				"heeeeeeee we are preparing now to play get ready !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		i++;
		System.out.println(i);
		System.out.println(
				"heeeeeeee we are preparing now to play get ready !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}

	@Pointcut(" execution (* com.Cv_Med.*.*(..))")
	public void doStuff() {
	}

	@Before("doStuff()")
	public void prepare_toplay1() {
		System.out.println(
				"heeeeeeee we are preparing now to play get ready !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
	}

	@Pointcut(value="execution(* com.Cv_Med.Controller.RestController.is*(..)) ")
	protected void allMethod() {
	}

	@Before(" allMethod() && args(request,user)")
	protected void sayHello(JoinPoint jp,HttpServletRequest request,UserJson user) {
		System.out.println("heeeeeeeeeeeeeeeeeeeeeeeeeloooooooooooooooooooooooooooooooo");
		System.out.println(jp.getTarget());
		/* Object[] signatureArgs = jp.getArgs();
		   for (Object signatureArg: signatureArgs) {
		      System.out.println("Arg: " + signatureArg);
		      
		   }*/
		System.out.println(user.getUsername());
		
		System.out.println("Login Auto Angular");
		boolean isConected=false;
		String username = user.getUsername();
		String password = user.getPassword();

		System.out.println(user.getPassword() + " " + user.getUsername());
		isConected = loginauto(request, username, password);
		System.out.println(isConected);
		
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

	/*@Before(value="execution(* com.Cv_Med.Controller.Controller_Cv.show_*(..)) && args(request)")
	public void ddd(HttpServletRequest request) {
		System.out.println("helo");
		System.out.println(request);
	}*/
	
	/*@Before("isRestController()")
	public void saywhat() {
		System.out.println("my name is diha f soq rasek");
	}*/

}
