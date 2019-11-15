package com.Cv_Med.JWT;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class JwtProperties {
	public static final String SECRET = "SomeSecretForJWTGeneration";
	public static final int EXPIRATION_TIME = 864_000_000; // 10 days
	public static final String TOKEN_PREFIX = "Bearer";
	public static final String HEADER_STRING = "Authorization";
	private String Token;
	public JwtProperties() {
		this.Token="";
	}
	public String getToken() {
		System.out.print("ready for geting token"+Token);
		return this.Token;
	}
	public void setToken(String Token) {
		System.out.print("ready for seting token"+Token);
		this.Token=Token;
	}
}
