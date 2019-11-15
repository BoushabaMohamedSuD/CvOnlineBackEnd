package com.Cv_Med.Hibernet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="formation")
public class Formation {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="formation")
	private String formation;
	@Column(name="establishemente")
	private String establishement;
	@Column(name="yearsBegun")
	private String yearsBegun;
	@Column(name="mounthsBegun")
	private String mounthsBegun;
	@Column(name="yearsLast")
	private String yearsLast;
	@Column(name="mounthsLast")
	private String mounthsLast;
	@Column(name="certificate")
	private String certificate ;
	@ManyToOne(targetEntity = Cv.class,cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="CvID",referencedColumnName = "id")
	private Cv CvId;
	
	public Formation() {
		
	}
	
	public Formation(String formation, String establishement, String yersBegun, String mounthsBegun, String yersLast,
			String mounthsLast,String certificate) {

		this.formation = formation;
		this.establishement = establishement;
		this.yearsBegun = yersBegun;
		this.mounthsBegun = mounthsBegun;
		this.yearsLast = yersLast;
		this.mounthsLast = mounthsLast;
		this.certificate=certificate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFormation() {
		return formation;
	}

	public String getEstablishement() {
		return establishement;
	}

	public String getYearsBegun() {
		return yearsBegun;
	}

	public String getMounthsBegun() {
		return mounthsBegun;
	}

	public String getYearsLast() {
		return yearsLast;
	}

	public String getMounthsLast() {
		return mounthsLast;
	}

	public void setFormation(String formation) {
		this.formation = formation;
	}

	public void setEstablishement(String establishement) {
		this.establishement = establishement;
	}

	public void setYearsBegun(String yersBegun) {
		this.yearsBegun = yersBegun;
	}

	public void setMounthsBegun(String mounthsBegun) {
		this.mounthsBegun = mounthsBegun;
	}

	public void setYearsLast(String yersLast) {
		this.yearsLast = yersLast;
	}

	public void setMounthsLast(String mounthsLast) {
		this.mounthsLast = mounthsLast;
	}

	public Cv getCvId() {
		return CvId;
	}

	public void setCvId(Cv cvId) {
		CvId = cvId;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

}
