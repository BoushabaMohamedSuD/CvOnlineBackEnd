package com.Cv_Med.Hibernet;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="pseudo")

	private String pseudo;
	@Column(name="email")
	
	private String email;
	@Column(name="password")
	private String password;
	@Column(name="kle_connection")
	private Boolean kle_connection;
	@Column(name="role")
	private String role;
	@Column(name="permession")
	private String permession;
	
	@OneToMany(targetEntity = Cv.class,mappedBy="user" ,cascade= {CascadeType.ALL},fetch=FetchType.LAZY)
	@JsonIgnore
	@JsonProperty(value = "cvs")
	private List<Cv> cvs;
	
	
	
	
	
	
	public User() {
		
		
	}
	
	
	
	public User(int id, String pseudo, String email, String password, Boolean kle_connection, String role, String permession) {
		
		this.id = id;
		this.pseudo = pseudo;
		this.email = email;
		this.password = password;
		this.kle_connection = kle_connection;
		this.role = role;
		this.permession = permession;
	}



	public User(String pseudo, String email, String password) {
		
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
	public boolean getKle_connection() {
		return kle_connection;
	}
	public String getRole() {
		return role;
	}
	public String getPermession() {
		return permession;
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
	public void setKle_connection(boolean kle_connection) {
		this.kle_connection=kle_connection;
	}
	public void setRole(String role) {
		this.role=role;
	}
	public void setPermession(String permession) {
		this.permession=permession;
	}



	public List<Cv> getCv() {
		return cvs;
	}



	public void setCv(List<Cv> cvs) {
		this.cvs = cvs;
	}
	
	
	public void addCv(Cv cv) {
		if(cvs!=null) {
			System.out.println("cvs null begun creation");
			cvs=new ArrayList<Cv>();
		}
		cvs.add(cv);
		cv.setUser(this);
		
	}

	public void clearCv() {
		this.cvs.clear();
	}
	public void deletCv(int id) {
		int index=0;
		for (Cv cv : cvs) {
			if(cv.getId()==id) {
				this.cvs.remove(index);
				break;
			}
			index++;
		}
	}
}
