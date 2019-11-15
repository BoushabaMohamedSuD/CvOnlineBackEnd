package com.Cv_Med.Hibernet;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class UserBuilder {

	private int id;

	private String pseudo;

	private String email;

	private String password;

	private Boolean kle_connection;

	private String role;

	private User user;

	public UserBuilder() {

		user = new User();
	}

	public UserBuilder setPseudo(String pseudo) {
		user.setPseudo(pseudo);
		return this;
	}

	public UserBuilder setEmail(String email) {
		user.setEmail(email);
		return this;
	}

	public UserBuilder setPassword(String password) {
		user.setPassword(password);
		return this;
	}

	public UserBuilder setKle_connection(Boolean kle_connection) {
		user.setKle_connection(kle_connection);
		return this;
	}

	public UserBuilder setRole(String role) {
		user.setRole(role);
		return this;
	}

	public UserBuilder setPermession(String permession) {
		user.setPermession(permession);
		return this;
	}

	public User getUser() {
		return user;
	}

}
