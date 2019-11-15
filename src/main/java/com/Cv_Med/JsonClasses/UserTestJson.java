package com.Cv_Med.JsonClasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public class UserTestJson {
	private String pseudo;
	private String email;
	public UserTestJson() {
		
	}
	public UserTestJson(String pseudo, String email) {
		
		this.pseudo = pseudo;
		this.email = email;
	}
	public String getPseudo() {
		return pseudo;
	}
	public String getEmail() {
		return email;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
