package com.Cv_Med.JWT;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;



public class CORSFilre implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse responseG, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) responseG;
		String urlOrigine="http://localhost:4200";
		//why i disabled those two configuration
		//because it block response when i send request in firefow
		//so i add a croos origine in restcontroller
		//but anyway if you disabled it wil l work fine in goolge chrome
		//but no in firefox
		
		//response.setHeader("Access-Control-Allow-Origin", urlOrigine);
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		//response.setHeader("Access-Control-Allow-Headers", urlOrigine);
		response.setHeader("Access-Control-Expose-Headers", "Authorization");
		response.addHeader("Access-Control-Expose-Headers", "Boushaba");
		response.addHeader("Access-Control-Expose-Headers", "Error");
		response.addHeader("Access-Control-Expose-Headers", "CvId");
		response.addHeader("Access-Control-Expose-Headers", "CvTitle");
		chain.doFilter(request, response);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	
}