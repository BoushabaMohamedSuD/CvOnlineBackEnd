package com.Cv_Med.JsonClasses;

import javax.persistence.Column;

public class LanguageJson {
	private int id;
	private String language;
	private String level;

	public LanguageJson() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LanguageJson(String language, String level) {
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
	

}
