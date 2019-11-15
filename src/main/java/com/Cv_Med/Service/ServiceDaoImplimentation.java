package com.Cv_Med.Service;

import java.text.Normalizer.Form;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import com.Cv_Med.Hibernet.Competence;
import com.Cv_Med.Hibernet.Cv;
import com.Cv_Med.Hibernet.Formation;
import com.Cv_Med.Hibernet.InfoPersonal;
import com.Cv_Med.Hibernet.Language;
import com.Cv_Med.Hibernet.Leisur;
import com.Cv_Med.Hibernet.User;
import com.Cv_Med.Hibernet.UserDaoImplimentation;
import com.Cv_Med.JsonClasses.CompetenceJson;
import com.Cv_Med.JsonClasses.CvJson;
import com.Cv_Med.JsonClasses.FormationJson;
import com.Cv_Med.JsonClasses.InfoPersonalJson;
import com.Cv_Med.JsonClasses.LanguageJson;
import com.Cv_Med.JsonClasses.LeisureJson;
import com.Cv_Med.JsonClasses.UserJson;
import com.Cv_Med.Test_Hibernet.CvTest;

@Service
@Scope("prototype")
public class ServiceDaoImplimentation implements ServiceDao {

	@Autowired
	private UserDaoImplimentation userDaoImplimentation;

	public ServiceDaoImplimentation() {

	}

	@Override
	public void add_user(User user) {
		userDaoImplimentation.add_user(user);

	}

	@Override
	public void remove_user(int id) {
		userDaoImplimentation.remove_user(id);

	}

	@Override
	public User show_user(int id) {
		return userDaoImplimentation.show_user(id);
	}

	@Override
	public User show_user(String pseudo) {
		return userDaoImplimentation.show_user(pseudo);
	}

	@Override
	public void update_user(int id, String pseudo, String email, String password) {
		userDaoImplimentation.update_user(id, pseudo, email, password);

	}
	
	@Override
	public void update_user_connection(User user, Boolean connection) {
		userDaoImplimentation.update_user_connection(user, connection);

	}

	@Override
	public void update_user_connection(int id, Boolean connection) {
		userDaoImplimentation.update_user_connection(id, connection);

	}

	@Override
	public List<User> show_users_connected() {
		List<User> users = userDaoImplimentation.show_users_connected();
		return users;
	}

	@Override
	public List<User> show_users() {

		return userDaoImplimentation.show_users();
	}

	/******** for testin g!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! */
	public User addCv(User usert, Cv cv) {

		User user = userDaoImplimentation.addcv(usert, cv);
		return user;

	}

	public User addCvJson(UserJson usert, Cv cv) {

		User user = userDaoImplimentation.addcvJson(usert, cv);
		return user;

	}

	public void addcvtest(CvTest cv) {
		userDaoImplimentation.addcvtest(cv);
	}

	public void addformationtest(Formation formation) {
		userDaoImplimentation.addformationtest(formation);
	}

	public Cv craeteCv(String UserName, Cv cv) {
		return userDaoImplimentation.createCv(UserName, cv);
	}

	public Cv updateCv(String UserName, List<Cv> cvs) {
		return userDaoImplimentation.updateCv(UserName, cvs);
	}
	

	public Cv deleteCv(String UserName, Cv cv) {
		return userDaoImplimentation.deleteCv(UserName, cv);
	}
	
	public List<CvJson> showCv(String UserName) {
		return userDaoImplimentation.showCv(UserName);
	}

	public InfoPersonal craeteInfoPersonal(String Username, String title, InfoPersonal info) {
		return userDaoImplimentation.creatInfoPersonale(Username, title, info);
	}

	public List<Formation> createFormation(String Username, String title, List<Formation> formation) {
		return userDaoImplimentation.createFormation(Username, title, formation);
	}

	public List<Competence> createCompetence(String Username, String title, List<Competence> competences) {
		return userDaoImplimentation.createCompetence(Username, title, competences);
	}

	public List<Language> createLanguages(String Username, String title, List<Language> languages) {
		return userDaoImplimentation.createLanguage(Username, title, languages);

	}

	public List<Leisur> createLeisur(String Username, String title, List<Leisur> leisures) {
		return userDaoImplimentation.createLeisure(Username, title, leisures);
	}
	
	public InfoPersonalJson showInfoPersonal(String Username, String title) {
		return userDaoImplimentation.showInfoPersonal(Username, title);
	}

	public List<FormationJson> showFormation(String Username, String title) {
		return userDaoImplimentation.showFormation(Username, title);
	}

	public List<CompetenceJson> showCompetence(String Username, String title) {
		return userDaoImplimentation.showCompetence(Username, title);
	}

	public List<LanguageJson> showLanguages(String Username, String title) {
		return userDaoImplimentation.showLanguage(Username, title);

	}

	public List<LeisureJson> showLeisur(String Username, String title) {
		return userDaoImplimentation.showLeisure(Username, title);
	}

}
