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
@Table(name="leisurefield")
public class Leisur {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="leisure")
	private String leisur;
	@ManyToOne(targetEntity = Cv.class,cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="CvID",referencedColumnName = "id")
	private Cv CvId;
	
	public Leisur() {
		
	}
	public Leisur(String leisur, String level) {
		
		this.leisur = leisur;
		
	}
	public String getLeisur() {
		return leisur;
	}

	public void setLeisur(String leisur) {
		this.leisur = leisur;
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
