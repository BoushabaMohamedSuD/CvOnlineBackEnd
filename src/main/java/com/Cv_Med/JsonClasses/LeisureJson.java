package com.Cv_Med.JsonClasses;

public class LeisureJson {
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String leisur;
	public LeisureJson() {
		
	}
	public LeisureJson(String leisur) {
		
		this.leisur = leisur;
	}
	public String getLeisur() {
		return leisur;
	}
	public void setLeisur(String leisur) {
		this.leisur = leisur;
	}
	

}
