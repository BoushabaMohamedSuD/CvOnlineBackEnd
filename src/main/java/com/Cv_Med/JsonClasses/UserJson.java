package com.Cv_Med.JsonClasses;

import javax.persistence.Column;

public class UserJson {
	private String username;
	private String password;
	private String email;
	private String confirm_password;
	
	
	
	
	public UserJson() {
		
	}
	
	public UserJson(String username, String email, String password,String confirm_password) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.confirm_password=confirm_password;
		
	}

	

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	public String getConfirm_Password() {
		return confirm_password;
	}


	public void setUsername(String pseudo) {
		this.username = pseudo;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public void setConfirm_password(String cpass) {
		this.confirm_password=cpass;
	}

	


}
