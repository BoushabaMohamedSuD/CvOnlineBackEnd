package com.Cv_Med.Hibernet;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import com.Cv_Med.JsonClasses.CompetenceJson;
import com.Cv_Med.JsonClasses.CvJson;
import com.Cv_Med.JsonClasses.FormationJson;
import com.Cv_Med.JsonClasses.InfoPersonalJson;
import com.Cv_Med.JsonClasses.LanguageJson;
import com.Cv_Med.JsonClasses.LeisureJson;
import com.Cv_Med.JsonClasses.UserJson;
import com.Cv_Med.Test_Hibernet.CvTest;

@Repository
@Scope("prototype")
public class UserDaoImplimentation implements UserDao {

	/*
	 * @Autowired
	 * 
	 * @Qualifier("sessionFactory")
	 */
	private SessionFactory sf;

	public UserDaoImplimentation() {

		this.sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.addAnnotatedClass(CvTest.class).addAnnotatedClass(Cv.class).addAnnotatedClass(InfoPersonal.class)
				.addAnnotatedClass(Formation.class).addAnnotatedClass(Competence.class)
				.addAnnotatedClass(Language.class).addAnnotatedClass(Leisur.class).addAnnotatedClass(Project.class)
				.buildSessionFactory();
	}

	// i add this constructor
	// because i need to inject this bean in a class
	// so i create anohter ben in the confiqueHibernet

	/*
	 * public UserDaoImplimentation(LocalSessionFactoryBean sessionFactory) {
	 * this.sf= sessionFactory; }
	 */

	@Override
	public void add_user(User user) {
		boolean kle_save = true;
		List<User> users = show_users();
		Session s = sf.getCurrentSession();
		if(!s.getTransaction().isActive()) {
			s.beginTransaction();
		}
		
		for (User st : users) {
			if ((user.getPseudo().equals(st.getPseudo())) || (user.getEmail().equals(st.getEmail()))) {
				kle_save = false;
				break;
			}
			System.out.println("ssssssssssssssssssssssssssssssssssssssssssssss");

		}
		if (kle_save) {
			s.save(user);
		} else {
			System.out.println("we can't save");
		}
		s.getTransaction().commit();
		s.close();

	}

	@Override
	public void remove_user(int id) {
		Session s = sf.getCurrentSession();
		if(!s.getTransaction().isActive()) {
			s.beginTransaction();
		}
		User user = s.get(User.class, id);
		s.delete(user);
		s.getTransaction().commit();
		s.close();

	}

	@Override
	public User show_user(int id) {

		Session s = sf.getCurrentSession();
		if(!s.getTransaction().isActive()) {
			s.beginTransaction();
		}
		User user = s.get(User.class, id);
		s.getTransaction().commit();
		s.close();
		return user;
	}

	@Override
	public User show_user(String pseudo) {
		List<User> users = show_users();
		Session s = sf.getCurrentSession();
		if(!s.getTransaction().isActive()) {
			s.beginTransaction();
		}
		for (User user : users) {
			if (user.getPseudo().equals(pseudo)) {
				s.getTransaction().commit();
				s.close();
				return user;
			}

		}
		s.getTransaction().commit();
		s.close();
		return null;

	}

	@Override
	public void update_user(int id, String pseudo, String email, String password) {
		boolean kle_pseudo_update = true;
		boolean kle_email_update = true;
		Session s = sf.getCurrentSession();
		if(!s.getTransaction().isActive()) {
			s.beginTransaction();
		}
		User user = s.get(User.class, id);

		List<User> Users = show_users();
		for (User st : Users) {
			if (pseudo.equals(st.getPseudo())) {

				if (kle_email_update) {
					kle_pseudo_update = false;
				} else {
					kle_pseudo_update = false;
					break;
				}

			}
			if (email.equals(st.getPassword())) {

				if (kle_pseudo_update) {
					kle_email_update = false;
				} else {
					kle_email_update = false;
					break;
				}

			}

		}
		if (kle_email_update) {
			user.setEmail(email);
		} else {
			System.out.println("email already existe");
		}
		if (kle_pseudo_update = false) {
			user.setPseudo(pseudo);
		} else {
			System.out.println("pseudo already existe");
		}

		user.setPassword(password);
		s.getTransaction().commit();
		s.close();

	}

	@Override
	public void update_user_connection(User user, Boolean connection) {
		Session s = sf.getCurrentSession();
		if(!s.getTransaction().isActive()) {
			s.beginTransaction();
		}
		User user1 = s.get(User.class, user.getId());
		user1.setKle_connection(connection);
		s.getTransaction().commit();
		s.close();

	}

	@Override
	public void update_user_connection(int id, Boolean connection) {
		Session s = sf.getCurrentSession();
		if(!s.getTransaction().isActive()) {
			s.beginTransaction();
		}
		User user = s.get(User.class, id);
		user.setKle_connection(connection);
		s.getTransaction().commit();
		s.close();

	}

	@Override
	public List<User> show_users_connected() {
		Session s = sf.getCurrentSession();
		if(!s.getTransaction().isActive()) {
			s.beginTransaction();
		}
		List<User> users = s.createQuery("from User u where u.kle_connection='true' ").getResultList();
		s.getTransaction().commit();
		s.close();
		return users;
	}

	@Override
	public List<User> show_users() {

		Session s = sf.getCurrentSession();
		if(!s.getTransaction().isActive()) {
			s.beginTransaction();
		}
		List<User> users = s.createQuery("from User").getResultList();
		s.getTransaction().commit();
		s.close();
		return users;
	}

	/* !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!///////////////////////////// */
	public Cv showCv(int id) {
		Session s = sf.getCurrentSession();
		if(!s.getTransaction().isActive()) {
			s.beginTransaction();
		}
		Cv cv = s.get(Cv.class, id);
		s.getTransaction().commit();
		s.close();
		return cv;

	}

	public List<Cv> show_Cvs() {

		Session s = sf.getCurrentSession();
		if(!s.getTransaction().isActive()) {
			s.beginTransaction();
		}
		List<Cv> cvs = s.createQuery("from cv").getResultList();
		s.getTransaction().commit();
		s.close();
		return cvs;
	}

	public List<CvJson> showCv(String UserName) {
		List<User> users = show_users();
		System.out.println("callin all the users");
		Session s = sf.getCurrentSession();
		if(!s.getTransaction().isActive()) {
			s.beginTransaction();
		}
		System.out.println("begun fetching the specifique user");
		for (User user : users) {
			if (user.getPseudo().equals(UserName)) {
				user = s.get(User.class, user.getId());
				System.out.println("getting the user in Database " + user.getPseudo());
				List<Cv> cvs = user.getCv();
				System.out.println("getting the list of cv");
				List<CvJson> cvJsons = new ArrayList<CvJson>();
				int i = 0;
				for (Cv cv : cvs) {
					System.out.println("cv index " + i + " title " + cv.getTitle());
					CvJson cvjson = new CvJson();
					cvjson.setTitle(cv.getTitle());
					cvjson.setId(cv.getId());
					cvJsons.add(cvjson);
					i++;
				}
				s.getTransaction().commit();
				s.close();
				System.out.println("returning the lsit of cv");

				return cvJsons;
			}
		}
		s.getTransaction().commit();
		s.close();
		return null;

	}

	public Cv createCv(String UserName, Cv cv) {
		List<User> users = show_users();
		Session s = sf.getCurrentSession();
		if(!s.getTransaction().isActive()) {
			s.beginTransaction();
		}
		for (User user : users) {
			if (user.getPseudo().equals(UserName)) {
				User userCv = s.get(User.class, user.getId());
				List<Cv> cvs = userCv.getCv();
				for (Cv cvuser : cvs) {
					if (cvuser.getTitle().equals(cv.getTitle())) {
						System.out.println("please you title is duplicated");
						s.getTransaction().commit();
						s.close();
						return null;
					}
				}
				userCv.addCv(cv);
				s.save(cv);
				s.getTransaction().commit();
				s.close();
				return cv;
			}
		}
		System.out.print("probleme with authentication");
		s.close();
		return null;
	}

	public Cv updateCv(String UserName, List<Cv> cvsUpdate) {
		List<User> users = show_users();
		Session s = sf.getCurrentSession();
		if(!s.getTransaction().isActive()) {
			s.beginTransaction();
		}
		Cv cv = null;
		for (User user : users) {
			if (user.getPseudo().equals(UserName)) {
				User userCv = s.get(User.class, user.getId());
				List<Cv> cvs = userCv.getCv();

				for (Cv cvuser : cvs) {
					System.out.println("title : " + cvuser.getTitle());
					if (cvuser.getTitle().equals(cvsUpdate.get(1).getTitle())) {
						System.out.println("please you title is duplicated");
						s.getTransaction().commit();
						s.close();
						return null;
					}
					if (cvuser.getTitle().equals(cvsUpdate.get(0).getTitle())) {
						cv = cvuser;
					}
				}
				if (cv != null) {
					System.out
							.println("update " + cvsUpdate.get(0).getTitle() + " with " + cvsUpdate.get(1).getTitle());
					cv.setTitle(cvsUpdate.get(1).getTitle());
					;
					s.getTransaction().commit();
					s.close();
					return cv;
				} else {
					System.out.println("the original cv hasn't be founded");
					return null;
				}

			}
		}
		System.out.print("probleme with authentication");
		s.close();
		return null;
	}

	public Cv deleteCv(String UserName, Cv cv) {
		List<User> users = show_users();
		Session s = sf.getCurrentSession();
		if(!s.getTransaction().isActive()) {
			s.beginTransaction();
		}
		for (User user : users) {
			if (user.getPseudo().equals(UserName)) {
				User userCv = s.get(User.class, user.getId());
				List<Cv> cvs = userCv.getCv();
				for (Cv cvuser : cvs) {
					if (cvuser.getTitle().equals(cv.getTitle())) {
						userCv.deletCv(cvuser.getId());
						s.delete(cvuser);
						// you have to save the modification in the user because cascade is treated with
						// hirechy down
						s.save(userCv);
						s.getTransaction().commit();
						s.close();
						return cvuser;
					}
				}
				System.out.println("cv dosn't existe");
				return null;
			}
		}
		System.out.print("probleme with authentication");
		s.close();
		return null;
	}

	public InfoPersonal creatInfoPersonale(String Username, String title, InfoPersonal info) {
		List<User> users = show_users();
		Session s = sf.getCurrentSession();
		if(!s.getTransaction().isActive()) {
			s.beginTransaction();
		}
		for (User user : users) {
			if (user.getPseudo().equals(Username)) {
				user = s.get(User.class, user.getId());
				List<Cv> cvs = user.getCv();
				for (Cv cv : cvs) {
					if (cv.getTitle().equals(title)) {
						// cv = s.get(Cv.class, cv.getId());
						InfoPersonal oldinfo = cv.getInfoPersonals();
						if (oldinfo != null) {
							oldinfo = s.get(InfoPersonal.class, oldinfo.getId());
							s.delete(oldinfo);
						}
						cv.addInfoPersonal(info);
						s.getTransaction().commit();
						s.close();
						return info;

					}
				}
				s.getTransaction().commit();
				s.close();
				return null;
			}
		}
		s.getTransaction().commit();
		s.close();
		return null;

	}

	public List<Formation> createFormation(String Username, String title, List<Formation> formations) {
		List<User> users = show_users();
		Session s = sf.getCurrentSession();
		if(!s.getTransaction().isActive()) {
			s.beginTransaction();
		}
		for (User user : users) {
			if (user.getPseudo().equals(Username)) {
				user = s.get(User.class, user.getId());
				List<Cv> cvs = user.getCv();
				for (Cv cv : cvs) {
					if (cv.getTitle().equals(title)) {
						// because we have an eager fetch
						// cv = s.get(Cv.class, cv.getId());
						System.out.println("begun cleare all the formation!!!!!!!!!!! in the id: " + cv.getId());
						List<Formation> oldformations = cv.getFormations();
						
						if (oldformations != null) {
							for (Formation oldformation : oldformations) {
								s.delete(oldformation);
							}
						}
						cv.clearFormation();
						cv.setOperationFormations(formations);
						for (Formation formation : formations) {
							s.save(formation);
							System.out.println(formation.getFormation() + " has been seved in database");
						}
						s.getTransaction().commit();
						s.close();
						return formations;
					}
				}
				s.getTransaction().commit();
				s.close();
				return null;
			}
		}
		s.getTransaction().commit();
		s.close();
		return null;
	}

	public List<Competence> createCompetence(String Username, String title, List<Competence> competences) {
		List<User> users = show_users();
		Session s = sf.getCurrentSession();
		if(!s.getTransaction().isActive()) {
			s.beginTransaction();
		}
		for (User user : users) {
			if (user.getPseudo().equals(Username)) {
				user = s.get(User.class, user.getId());
				List<Cv> cvs = user.getCv();
				for (Cv cv : cvs) {
					if (cv.getTitle().equals(title)) {
						// because we have an eager fetch
						// cv = s.get(Cv.class, cv.getId());
						System.out.println("begun cleare all the formation!!!!!!!!!!! in the id: " + cv.getId());
						List<Competence> oldcompetences = cv.getCompetences();
						if (oldcompetences != null) {
							for (Competence oldcompetence : oldcompetences) {
								s.delete(oldcompetence);
							}
						}
						cv.clearCompetence();
						cv.setOperationCompetence(competences);
						for (Competence competence : competences) {
							s.save(competence);
							System.out.println(competence.getCompetence() + " has been seved in database");
						}
						s.getTransaction().commit();
						s.close();
						return competences;
					}
				}
				s.getTransaction().commit();
				s.close();
				return null;
			}
		}
		s.getTransaction().commit();
		s.close();
		return null;
	}

	public List<Language> createLanguage(String Username, String title, List<Language> languages) {
		List<User> users = show_users();
		Session s = sf.getCurrentSession();
		if(!s.getTransaction().isActive()) {
			s.beginTransaction();
		}
		for (User user : users) {
			if (user.getPseudo().equals(Username)) {
				user = s.get(User.class, user.getId());
				List<Cv> cvs = user.getCv();
				for (Cv cv : cvs) {
					if (cv.getTitle().equals(title)) {
						// because we have an eager fetch
						// cv = s.get(Cv.class, cv.getId());
						System.out.println("begun cleare all the formation!!!!!!!!!!! in the id: " + cv.getId());
						List<Language> oldlanguages = cv.getLanguages();
						if (oldlanguages != null) {
							for (Language oldlanguage : oldlanguages) {
								s.delete(oldlanguage);
							}
						}
						cv.clearLanguages();
						cv.setOperationLanguage(languages);
						for (Language language : languages) {
							s.save(language);
							System.out.println(language.getLanguage() + " has been seved in database");
						}
						s.getTransaction().commit();
						s.close();
						return languages;
					}
				}
				s.getTransaction().commit();
				s.close();
				return null;
			}
		}
		s.getTransaction().commit();
		s.close();
		return null;
	}

	public List<Leisur> createLeisure(String Username, String title, List<Leisur> leisures) {
		List<User> users = show_users();
		Session s = sf.getCurrentSession();
		if(!s.getTransaction().isActive()) {
			s.beginTransaction();
		}
		for (User user : users) {
			if (user.getPseudo().equals(Username)) {
				user = s.get(User.class, user.getId());
				List<Cv> cvs = user.getCv();
				for (Cv cv : cvs) {
					if (cv.getTitle().equals(title)) {
						// because we have an eager fetch
						// cv = s.get(Cv.class, cv.getId());
						System.out.println("begun cleare all the formation!!!!!!!!!!! in the id: " + cv.getId());
						List<Leisur> oldleisures = cv.getLeisures();
						if (oldleisures != null) {
							for (Leisur oldleisur : oldleisures) {
								s.delete(oldleisur);
							}
						}
						cv.clearLeisures();
						cv.setOperationLeisure(leisures);
						for (Leisur leisur : leisures) {
							s.save(leisur);
							System.out.println(leisur.getLeisur() + " has been seved in database");
						}
						s.getTransaction().commit();
						s.close();
						return leisures;
					}
				}
				s.getTransaction().commit();
				s.close();
				return null;
			}
		}
		s.getTransaction().commit();
		s.close();
		return null;
	}

	public InfoPersonalJson showInfoPersonal(String Username, String title) {
		List<User> users = show_users();
		Session s = sf.getCurrentSession();
		if(!s.getTransaction().isActive()) {
			s.beginTransaction();
		}
		for (User user : users) {
			if (user.getPseudo().equals(Username)) {
				user = s.get(User.class, user.getId());
				List<Cv> cvs = user.getCv();
				if(cvs!=null) {
					for (Cv cv : cvs) {
						if (cv.getTitle().equals(title)) {
							// cv = s.get(Cv.class, cv.getId());
							InfoPersonal oldinfo = cv.getInfoPersonals();
							if(oldinfo==null) {
								return null;
							}
							InfoPersonalJson infoJson=new InfoPersonalJson();
							infoJson.setId(oldinfo.getId());
							infoJson.setAdress(oldinfo.getAdress());
							infoJson.setEmail(oldinfo.getEmail());
							infoJson.setFirstname(oldinfo.getFirstname());
							infoJson.setImage(oldinfo.getImage());
							infoJson.setLastname(oldinfo.getLastname());
							infoJson.setNbrtele(oldinfo.getNbrtele());
							s.getTransaction().commit();
							s.close();
							return infoJson;

						}
					}
				}
				
				s.getTransaction().commit();
				s.close();
				return null;
			}
		}
		s.getTransaction().commit();
		s.close();
		return null;

	}

	public List<FormationJson> showFormation(String Username, String title) {
		List<User> users = show_users();
		Session s = sf.getCurrentSession();
		if(!s.getTransaction().isActive()) {
			s.beginTransaction();
		}
		for (User user : users) {
			if (user.getPseudo().equals(Username)) {
				user = s.get(User.class, user.getId());
				List<Cv> cvs = user.getCv();
				for (Cv cv : cvs) {
					if (cv.getTitle().equals(title)) {
						// because we have an eager fetch
						// cv = s.get(Cv.class, cv.getId());
						System.out.println("begun cleare all the formation!!!!!!!!!!! in the id: " + cv.getId());
						List<Formation> oldformations = cv.getFormations();
						List<FormationJson> formationJsons = new ArrayList<FormationJson>();
						int i = 0;
						for ( Formation formation : oldformations) {
							System.out.println("cv index " + i + " title " + formation.getId());
							FormationJson formationJson=new FormationJson(); 
							formationJson.setId(formation.getId());
							formationJson.setCertificate(formation.getCertificate());
							formationJson.setEstablishement(formation.getEstablishement());
							formationJson.setFormation(formation.getFormation());
							formationJson.setMounthsBegun(formation.getMounthsBegun());
							formationJson.setMounthsLast(formation.getMounthsLast());
							formationJson.setYearsBegun(formation.getYearsBegun());
							formationJson.setYearsLast(formation.getYearsLast());
							formationJsons.add(formationJson);
							i++;
						}
						
						s.getTransaction().commit();
						s.close();
						return formationJsons;
					}
				}
				s.getTransaction().commit();
				s.close();
				return null;
			}
		}
		s.getTransaction().commit();
		s.close();
		return null;
	}

	public List<CompetenceJson> showCompetence(String Username, String title) {
		List<User> users = show_users();
		Session s = sf.getCurrentSession();
		if(!s.getTransaction().isActive()) {
			s.beginTransaction();
		}
		for (User user : users) {
			if (user.getPseudo().equals(Username)) {
				user = s.get(User.class, user.getId());
				List<Cv> cvs = user.getCv();
				for (Cv cv : cvs) {
					if (cv.getTitle().equals(title)) {
						// because we have an eager fetch
						// cv = s.get(Cv.class, cv.getId());
						System.out.println("begun cleare all the formation!!!!!!!!!!! in the id: " + cv.getId());
						List<Competence> oldcompetences = cv.getCompetences();
						List<CompetenceJson> competencJsons=new ArrayList<CompetenceJson>();
						int i = 0;
						for ( Competence competence : oldcompetences) {
							System.out.println("cv index " + i + " title " + competence.getId());
							CompetenceJson competencJson=new CompetenceJson();
							competencJson.setId(competence.getId());
							competencJson.setCompetence(competence.getCompetence());
							competencJson.setLevel(competence.getLevel());
							competencJsons.add(competencJson);
							i++;
						}
						
						s.getTransaction().commit();
						s.close();
						System.out.println(competencJsons);
						return competencJsons;
					}
				}
				s.getTransaction().commit();
				s.close();
				return null;
			}
		}
		s.getTransaction().commit();
		s.close();
		return null;
	}

	public List<LanguageJson> showLanguage(String Username, String title) {
		List<User> users = show_users();
		Session s = sf.getCurrentSession();
		if(!s.getTransaction().isActive()) {
			s.beginTransaction();
		}
		for (User user : users) {
			if (user.getPseudo().equals(Username)) {
				user = s.get(User.class, user.getId());
				List<Cv> cvs = user.getCv();
				for (Cv cv : cvs) {
					if (cv.getTitle().equals(title)) {
						// because we have an eager fetch
						// cv = s.get(Cv.class, cv.getId());
						System.out.println("begun cleare all the formation!!!!!!!!!!! in the id: " + cv.getId());
						List<Language> oldlanguages = cv.getLanguages();
						List<LanguageJson> languageJsons=new ArrayList<LanguageJson>();
						int i = 0;
						for ( Language langue : oldlanguages) {
							System.out.println("cv index " + i + " title " + langue.getId());
							LanguageJson languageJson=new LanguageJson();
							languageJson.setId(langue.getId());
							languageJson.setLanguage(langue.getLanguage());
							languageJson.setLevel(langue.getLevel());
							languageJsons.add(languageJson);
							i++;
						}
						s.getTransaction().commit();
						s.close();
						return languageJsons;
					}
				}
				s.getTransaction().commit();
				s.close();
				return null;
			}
		}
		s.getTransaction().commit();
		s.close();
		return null;
	}

	public List<LeisureJson> showLeisure(String Username, String title) {
		List<User> users = show_users();
		Session s = sf.getCurrentSession();
		if(!s.getTransaction().isActive()) {
			s.beginTransaction();
		}
		for (User user : users) {
			if (user.getPseudo().equals(Username)) {
				user = s.get(User.class, user.getId());
				List<Cv> cvs = user.getCv();
				for (Cv cv : cvs) {
					if (cv.getTitle().equals(title)) {
						// because we have an eager fetch
						// cv = s.get(Cv.class, cv.getId());
						System.out.println("begun cleare all the formation!!!!!!!!!!! in the id: " + cv.getId());
						List<Leisur> oldleisures = cv.getLeisures();
						List<LeisureJson> leisureJsons=new ArrayList<LeisureJson>();
						int i = 0;
						for ( Leisur leisur : oldleisures) {
							System.out.println("cv index " + i + " title " + leisur.getId());
							LeisureJson leisureJson=new LeisureJson();
							leisureJson.setId(leisur.getId());
							leisureJson.setLeisur(leisur.getLeisur());
							leisureJsons.add(leisureJson);
							i++;
						}
						s.getTransaction().commit();
						s.close();
						return leisureJsons;
					}
				}
				s.getTransaction().commit();
				s.close();
				return null;
			}
		}
		s.getTransaction().commit();
		s.close();
		return null;
	}

	// for testing!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

	public void addcvtest(CvTest cv) {
		Session s = sf.getCurrentSession();
		if(!s.getTransaction().isActive()) {
			s.beginTransaction();
		}
		s.save(cv);
		s.getTransaction().commit();
		s.close();

	}

	public void addformationtest(Formation formation) {
		Session s = sf.getCurrentSession();
		if(!s.getTransaction().isActive()) {
			s.beginTransaction();
		}
		Cv cv = s.get(Cv.class, 1);
		cv.addFormation(formation);
		s.save(formation);
		s.getTransaction().commit();
		s.close();

	}

	public User addcv(User usert, Cv cv) {
		List<User> users = show_users();
		Session s = sf.getCurrentSession();
		if(!s.getTransaction().isActive()) {
			s.beginTransaction();
		}
		s.save(cv);
		for (User user : users) {
			if (user.getPseudo().equals(usert.getPseudo())) {

				user.addCv(cv);
				s.save(cv);
				s.getTransaction().commit();
				s.close();
				return user;
			}
		}
		// User user = s.get(User.class, id);
		s.getTransaction().commit();
		s.close();
		return null;

	}

	public User addcvJson(UserJson usert, Cv cv) {
		List<User> users = show_users();

		Session s = sf.getCurrentSession();
		if(!s.getTransaction().isActive()) {
			s.beginTransaction();
		}
		for (User user : users) {
			if (user.getPseudo().equals(usert.getUsername())) {

				User userfinale = s.get(User.class, user.getId());
				userfinale.addCv(cv);
				s.save(cv);
				System.out.println("save it");
				s.getTransaction().commit();
				s.close();
				return userfinale;
			}
		}
		// User user = s.get(User.class, id);
		s.getTransaction().commit();
		s.close();
		return null;

	}

	public void IamHer() {
		System.out.println("i am her dao");
	}

}
