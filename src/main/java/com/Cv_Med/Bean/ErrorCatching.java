package com.Cv_Med.Bean;

import org.springframework.stereotype.Component;

//it's defined in configueBean
public class ErrorCatching {
	private String Error;
	public ErrorCatching() {
		this.Error="";
	}
	public String getErrot() {
		return this.Error;
	}
	public void setError(String Error) {
		this.Error=Error;
	}
	public void IamHer() {
		System.out.println("i am here catching Error");
	}

}
