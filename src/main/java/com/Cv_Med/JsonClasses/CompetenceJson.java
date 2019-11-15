package com.Cv_Med.JsonClasses;

public class CompetenceJson {
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String competence;
	private String level;
	public CompetenceJson() {
		
	}
	public CompetenceJson(String competence, String level) {
		this.competence = competence;
		this.level = level;
	}
	public String getCompetence() {
		return competence;
	}
	public String getLevel() {
		return level;
	}
	public void setCompetence(String competence) {
		this.competence = competence;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
}
