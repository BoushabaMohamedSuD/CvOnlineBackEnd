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
@Table(name="competence")
public class Competence {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="competence")
	private String competence;
	@Column(name="Level")
	private String level;
	@ManyToOne(targetEntity = Cv.class,cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="CvID",referencedColumnName = "id")
	private Cv CvId;
	public Competence() {
		
	}
	public Competence(String competence, String level) {
		
		this.competence = competence;
		this.level = level;
	}
	public String getCompetence() {
		return competence;
	}
	public String getLevel() {
		return this.level;
	}
	public void setCompetence(String competence) {
		this.competence = competence;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cv getCvId() {
		return CvId;
	}
	public void setCvId(Cv cvId) {
		CvId = cvId;
	}
	

}
