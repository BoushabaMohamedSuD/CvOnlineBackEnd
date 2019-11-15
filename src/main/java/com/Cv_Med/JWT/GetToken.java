package com.Cv_Med.JWT;

import org.springframework.stereotype.Component;

@Component
public class GetToken {
	private String Token;

	public GetToken() {
		
		this.Token = "null now token";
	}

	public String getToken() {
		System.out.print("ready for geting token"+Token);
		return this.Token;
	}

	public void setToken(String token) {
		System.out.print("ready for seting token"+ Token);
		this.Token = token;
	}
	

}
