package com.Cv_Med.Hibernet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="personalinfo")
public class InfoPersonal {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="firstname")
	private String firstname;
	@Column(name="lastname")
	private String lastname;
	@Column(name="email")
	private String email;
	@Column(name="adress")
	private String adress;
	@Column(name="nbrtele")
	private int nbrtele;
	@Column(name="image")
	private String image;
	@OneToOne(targetEntity = Cv.class,cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="CvID",referencedColumnName = "id")
	private Cv CvId;
	
	public InfoPersonal() {
		this.image="testImageUplaod";
	}
	
	public InfoPersonal(String firstname, String lastname, String email, String adrees, int nbrtele, String image) {
		
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.adress = adrees;
		this.nbrtele = nbrtele;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAdress(String adrees) {
		this.adress = adrees;
	}

	public void setNbrtele(int nbrtele) {
		this.nbrtele = nbrtele;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Cv getCvId() {
		return CvId;
	}

	public void setCvId(Cv cvId) {
		this.CvId = cvId;
	}
	

}
