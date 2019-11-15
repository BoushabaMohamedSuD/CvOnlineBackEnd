package com.Cv_Med.JsonClasses;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class InfoPersonalJson {
	private int id;
	private String firstname;
	private String lastname;
	private String email;
	private String adress;
	private int nbrtele;
	private String image;

	public InfoPersonalJson(int id, String firstname, String lastname, String email, String adress, int nbrtele,
			String image) {
		
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.adress = adress;
		this.nbrtele = nbrtele;
		this.image = image;
	}
	public InfoPersonalJson() {
		
	}

	public int getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getEmail() {
		return email;
	}

	public String getAdress() {
		return adress;
	}

	public int getNbrtele() {
		return nbrtele;
	}

	public String getImage() {
		return image;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public void setNbrtele(int nbrtele) {
		this.nbrtele = nbrtele;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}
