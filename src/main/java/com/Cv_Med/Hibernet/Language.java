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
@Table(name="language")
public class Language {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="language")
	private String language;
	@Column(name="level")
	private String level;
	@ManyToOne(targetEntity = Cv.class,cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="CvID",referencedColumnName = "id")
	private Cv CvId;
	
	public Language() {
		
	}
	public Language(String language, String level) {
		
		this.language = language;
		this.level = level;
	}
	public String getLanguage() {
		return language;
	}
	public String getLevel() {
		return level;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getId() {
		return id;
	}
	public Cv getCvId() {
		return CvId;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCvId(Cv cvId) {
		CvId = cvId;
	}
	
	

}
