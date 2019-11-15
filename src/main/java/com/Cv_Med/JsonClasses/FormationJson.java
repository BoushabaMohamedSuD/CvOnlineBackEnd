package com.Cv_Med.JsonClasses;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class FormationJson {

	private int id;

	private String formation;

	private String establishement;

	private String yearsBegun;

	private String mounthsBegun;

	private String yearsLast;

	private String mounthsLast;
	
	private String certificate;

	public FormationJson() {

	}

	public FormationJson(int id, String formation, String establishement, String yearsBegun, String mounthsBegun,
			String yearsLast, String mounthsLast,String Certificate) {

		this.id = id;
		this.formation = formation;
		this.establishement = establishement;
		this.yearsBegun = yearsBegun;
		this.mounthsBegun = mounthsBegun;
		this.yearsLast = yearsLast;
		this.mounthsLast = mounthsLast;
		this.certificate=Certificate;
	}

	public int getId() {
		return id;
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

	public void setId(int id) {
		this.id = id;
	}

	public void setFormation(String formation) {
		this.formation = formation;
	}

	public void setEstablishement(String establishement) {
		this.establishement = establishement;
	}

	public void setYearsBegun(String yearsBegun) {
		this.yearsBegun = yearsBegun;
	}

	public void setMounthsBegun(String mounthsBegun) {
		this.mounthsBegun = mounthsBegun;
	}

	public void setYearsLast(String yearsLast) {
		this.yearsLast = yearsLast;
	}

	public void setMounthsLast(String mounthsLast) {
		this.mounthsLast = mounthsLast;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
}
