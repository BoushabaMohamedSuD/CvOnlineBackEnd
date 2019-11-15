package com.Cv_Med.Hibernet;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="cv")
public class Cv {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="title")
	private String title;
	
	//don't use delete cascade if we delete cv user shouldn't be deleted
	
	@ManyToOne(targetEntity = User.class,cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="UserID",referencedColumnName = "id")
	@JsonIgnore
	@JsonProperty(value = "user")
	private User user;
	
	@OneToOne(targetEntity = InfoPersonal.class,mappedBy="CvId" ,cascade= {CascadeType.ALL},fetch=FetchType.LAZY)
	@JsonIgnore
	@JsonProperty(value = "infoPersonals")
	private InfoPersonal infoPersonals;
	@OneToMany(targetEntity = Formation.class,mappedBy="CvId" ,cascade= {CascadeType.ALL},fetch=FetchType.LAZY)
	@JsonIgnore
	@JsonProperty(value = "formation")
	private List<Formation> formations;
	@OneToMany(targetEntity = Competence.class,mappedBy="CvId" ,cascade= {CascadeType.ALL},fetch=FetchType.LAZY)
	@JsonIgnore
	@JsonProperty(value = "competences")
	private List<Competence> competences;
	@OneToMany(targetEntity = Language.class,mappedBy="CvId" ,cascade= {CascadeType.ALL},fetch=FetchType.LAZY)
	@JsonIgnore
	@JsonProperty(value = "languages")
	private List<Language> languages;
	@OneToMany(targetEntity = Leisur.class,mappedBy="CvId" ,cascade= {CascadeType.ALL},fetch=FetchType.LAZY)
	@JsonIgnore
	@JsonProperty(value = "Leisures")
	private List<Leisur> Leisures;

	public Cv() {
	}

	public Cv(int id, User user) {

		this.id = id;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	

	public User getUser() {
		return user;
	}

	public void setId(int id) {
		this.id = id;
	}


	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	

	public List<Formation> getFormations() {
		return formations;
	}

	public List<Competence> getCompetences() {
		return competences;
	}

	public List<Language> getLanguages() {
		return languages;
	}

	public List<Leisur> getLeisures() {
		return Leisures;
	}

	

	public void setFormations(List<Formation> formations) {
		this.formations = formations;
	}

	public void setCompetences(List<Competence> competences) {
		this.competences = competences;
	}

	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}

	public void setLeisures(List<Leisur> leisures) {
		Leisures = leisures;
	}
	
	
	public InfoPersonal getInfoPersonals() {
		return infoPersonals;
	}

	public void setInfoPersonals(InfoPersonal infoPersonals) {
		this.infoPersonals = infoPersonals;
	}

	
	public void addInfoPersonal(InfoPersonal info) {
		this.infoPersonals=info;
		this.infoPersonals.setCvId(this);
	}
	public void addFormation(Formation formation) {
		if(formations!=null) {
			System.out.println("cvs null begun creation");
			formations=new ArrayList<Formation>();
		}
		formations.add(formation);
		formation.setCvId(this);
		
	}
	public void addCompetence(Competence competence) {
		if(competences!=null) {
			System.out.println("cvs null begun creation");
			competences=new ArrayList<Competence>();
		}
		competences.add(competence);
		competence.setCvId(this);
		
	}
	public void addLanguage(Language lang) {
		if(languages!=null) {
			System.out.println("cvs null begun creation");
			languages=new ArrayList<Language>();
		}
		languages.add(lang);
		lang.setCvId(this);
		
	}
	public void addLeisure(Leisur leisur) {
		if(infoPersonals!=null) {
			System.out.println("cvs null begun creation");
			Leisures=new ArrayList<Leisur>();
		}
		Leisures.add(leisur);
		leisur.setCvId(this);
		
	}

   /* public void setOperationInfopersonals(List<InfoPersonal> infos) {
    	this.infoPersonals=infos;
    	for (InfoPersonal info : infos) {
			info.setCvId(this);
		}
    }*/
    public void setOperationFormations(List<Formation> formations) {
    	this.formations=formations;
    	for (Formation formation : formations) {
			formation.setCvId(this);
		}
    }
    public void setOperationCompetence(List<Competence> competences) {
    	this.competences=competences;
    	for (Competence competence : competences) {
			competence.setCvId(this);
		}
    }
    public void setOperationLanguage(List<Language>  languases) {
    	this.languages=languases;
    	for (Language language : languases) {
			language.setCvId(this);
		}
    }
    public void setOperationLeisure(List<Leisur> leisures) {
    	this.Leisures=leisures;
    	for (Leisur leisur : Leisures) {
			leisur.setCvId(this);
		}
    }
   
	
	
 	public void clearFormation() {
		this.formations.clear();
	}
	public void clearCompetence() {
		this.competences.clear();
	}
	public void clearLanguages() {
		this.languages.clear();
	}
	public void clearLeisures() {
		this.Leisures.clear();
	}
	public void clearprojects() {
		
	}
}
