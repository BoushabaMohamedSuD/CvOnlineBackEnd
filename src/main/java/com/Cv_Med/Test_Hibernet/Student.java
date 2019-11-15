package com.Cv_Med.Test_Hibernet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="pseudo")
	private String pseudo;
	@Column(name="email")
	private String email;
	@Column(name="passworld")
	private String password;
	
	
	public Student() {
		
	}
	
	public Student( String pseudo, String email, String password) {
		
		
		this.pseudo = pseudo;
		this.email = email;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	public String getPseudo() {
		return pseudo;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
