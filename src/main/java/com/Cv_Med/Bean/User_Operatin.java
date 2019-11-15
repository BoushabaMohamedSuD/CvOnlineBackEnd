package com.Cv_Med.Bean;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.Cv_Med.Hibernet.Competence;
import com.Cv_Med.Hibernet.Cv;
import com.Cv_Med.Hibernet.Formation;
import com.Cv_Med.Hibernet.InfoPersonal;
import com.Cv_Med.Hibernet.Language;
import com.Cv_Med.Hibernet.Leisur;
import com.Cv_Med.Hibernet.User;
import com.Cv_Med.Hibernet.UserBuilder;
import com.Cv_Med.JsonClasses.CompetenceJson;
import com.Cv_Med.JsonClasses.CvJson;
import com.Cv_Med.JsonClasses.FormationJson;
import com.Cv_Med.JsonClasses.InfoPersonalJson;
import com.Cv_Med.JsonClasses.LanguageJson;
import com.Cv_Med.JsonClasses.LeisureJson;
import com.Cv_Med.Service.ServiceDaoImplimentation;

//prototype scope
//when you add @compenet or @service @reposotry @controler that's mean 
//tha spring is add those classes as beans wiht default name is the name 
//of the class the first letter undercase so you have to chek that 
//no one of the beans that are in beansconfique.xml has the default name 
//because in this case spring will confuse , because there are two 
//beans with the same name and that make an errore
//so in thise case you can make use of @autowired directly without qualifing anything

@Component
@Scope("prototype")
public class User_Operatin implements Operation {

	@Autowired
	private ServiceDaoImplimentation serviceDaoImplimentation;
	@Autowired
	private UserBuilder userBuilder;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User_Operatin() {

	}

	@Override
	public User login(String pseudo, String email, String password) {

		if (pseudo == null || email == null || password == null) {
			System.out.println("you miss one of those fields");
			return null;
		} else {
			User user = serviceDaoImplimentation.show_user(pseudo);
			if (user == null) {
				System.out.println("error in recognition");
				return null;
			} else {
				if (user.getKle_connection()) {
					System.out.println("user is already connected");
					return null;
				} else {
					if (user.getPassword().equals(password) && user.getEmail().equals(email)) {
						serviceDaoImplimentation.update_user_connection(user, true);
						System.out.println("login successfly");
						return user;
					} else {
						System.out.println("error in password");
						return null;
					}

				}

			}

		}

	}

	@Override
	public User registre(String pseudo, String email, String password, String confirm_password) {
		if (pseudo == null || email == null || password == null | confirm_password == null) {
			System.out.println("you miss one of those fields");
			return null;
		} else {
			if (password.equals(confirm_password)) {

				List<User> usrs = serviceDaoImplimentation.show_users();
				for (User user : usrs) {
					if (user.getPseudo().equals(pseudo) || user.getEmail().equals(email)) {
						System.out.println("this account has already existe");
						return null;
					}
				}

				User user = new User(pseudo, email, password);

				serviceDaoImplimentation.add_user(user);
				serviceDaoImplimentation.update_user_connection(user, true);
				return user;
			} else {
				System.out.println("confirm you password please");
				return null;
			}

		}
	}

	@Override
	public void logout(int id) {

		serviceDaoImplimentation.update_user_connection(id, false);

	}

	// for spring securty!!!!!!!!!!!!!!!!!!!!
	public User registreSecurty(String pseudo, String email, String password, String confirm_password) {
		if (pseudo == null || email == null || password == null | confirm_password == null) {
			System.out.println("you miss one of those fields");
			return null;
		} else {
			if (password.equals(confirm_password)) {

				List<User> usrs = serviceDaoImplimentation.show_users();
				for (User user : usrs) {
					if (user.getPseudo().equals(pseudo) || user.getEmail().equals(email)) {
						System.out.println("this account has already existe");
						return null;
					}
				}
				String password_secure = passwordEncoder.encode(password);
				User user = userBuilder.setPseudo(pseudo).setEmail(email).setPassword(password_secure).setRole("USER")
						.setKle_connection(false).getUser();

				serviceDaoImplimentation.add_user(user);
				serviceDaoImplimentation.update_user_connection(user, true);
				return user;
			} else {
				System.out.println("confirm you password please");
				return null;
			}

		}

	}

	// for uplaoding image
	public void UplaodImage(MultipartFile file, String UserName, String CvId, String TypeProfile, String nameFile) {
		System.out.println("---------Upload----------");
		/*
		 * try { user_Operatin.saveImage(image); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		String msg = "";
		if (!file.isEmpty()) {
			BufferedOutputStream bos = null;
			try {
				byte[] fileBytes = file.getBytes();
				// location to save the file
				System.out.print(file.getOriginalFilename());
				String fileName = "C:" + File.separator + "CvProData" + File.separator + UserName + File.separator
						+ CvId + File.separator + TypeProfile + File.separator + nameFile;
				System.out.println(fileName);
				File f = new File(fileName);
				System.out.println(f.getFreeSpace());

				f.getParentFile().mkdirs();
				try {
					f.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				bos = new BufferedOutputStream(new FileOutputStream(f));
				bos.write(fileBytes);
				msg = "Upload successful for " + file.getName();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (bos != null) {
					try {
						bos.close();
						System.out.println(bos.toString().getBytes());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} else {
			msg = "Upload failed for " + file.getName() + " as file is empty";
		}

		System.out.println(msg);
		System.out.println("done");
	}

	// for cv elements
	public Cv createCv(String UserName, Cv cv) {
		return serviceDaoImplimentation.craeteCv(UserName, cv);
	}

	public Cv updateCv(String UserName, List<Cv> cvs) {
		return serviceDaoImplimentation.updateCv(UserName, cvs);
	}

	public boolean deleteCv(String UserName, Cv cv) {
		Cv cvDeleted=serviceDaoImplimentation.deleteCv(UserName, cv);
		
		if(cvDeleted==null) {
			System.out.println("Error deleting cv");
			return false;
		}else {
			String srcDirecrty = "C:" + File.separator + "CvProData" + File.separator + UserName + File.separator
					+ cvDeleted.getId();
			File directory = new File(srcDirecrty);

			// make sure directory exists
			if (!directory.exists()) {

				System.out.println("Directory does not exist.");
				return true;

			} else {

				try {

					delete(directory);

				} catch (IOException e) {
					e.printStackTrace();
					System.exit(0);
				}
			}

			System.out.println("Done");
			
			return true;
			
		}
	
		
	}

	public static void delete(File file) throws IOException {

		if (file.isDirectory()) {

			// directory is empty, then delete it
			if (file.list().length == 0) {

				file.delete();
				System.out.println("Directory is deleted : " + file.getAbsolutePath());

			} else {

				// list all the directory contents
				String files[] = file.list();

				for (String temp : files) {
					// construct the file structure
					File fileDelete = new File(file, temp);

					// recursive delete
					delete(fileDelete);
				}

				// check the directory again, if empty then delete it
				if (file.list().length == 0) {
					file.delete();
					System.out.println("Directory is deleted : " + file.getAbsolutePath());
				}
			}

		} else {
			// if file, then delete it
			file.delete();
			System.out.println("File is deleted : " + file.getAbsolutePath());
		}

	}

	public List<CvJson> showCv(String UserName) {
		return serviceDaoImplimentation.showCv(UserName);
	}

	public List<Formation> createFormation(String Username, String title, List<Formation> formation) {
		return serviceDaoImplimentation.createFormation(Username, title, formation);
	}

	public InfoPersonal createInfoPersonal(String Username, String title, InfoPersonal info) {
		return serviceDaoImplimentation.craeteInfoPersonal(Username, title, info);
	}

	public List<Competence> createCompetence(String Username, String title, List<Competence> competences) {
		return serviceDaoImplimentation.createCompetence(Username, title, competences);
	}

	public List<Language> createLanguages(String Username, String title, List<Language> languages) {
		return serviceDaoImplimentation.createLanguages(Username, title, languages);

	}

	public List<Leisur> createLeisur(String Username, String title, List<Leisur> leisures) {
		return serviceDaoImplimentation.createLeisur(Username, title, leisures);
	}

	
	public List<FormationJson> showFormation(String Username, String title) {
		return serviceDaoImplimentation.showFormation(Username, title);
	}

	public InfoPersonalJson showInfoPersonal(String Username, String title) {
		return serviceDaoImplimentation.showInfoPersonal(Username, title);
	}

	public List<CompetenceJson> showCompetence(String Username, String title) {
		return serviceDaoImplimentation.showCompetence(Username, title);
	}

	public List<LanguageJson> showLanguages(String Username, String title) {
		return serviceDaoImplimentation.showLanguages(Username, title);

	}

	public List<LeisureJson> showLeisur(String Username, String title) {
		return serviceDaoImplimentation.showLeisur(Username, title);
	}

	
	
	
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!for the test!!!!!!!!!!!!!!!!!!!!!\\
	public void saveImage(MultipartFile image) throws IOException {
		String folder = "C:\\Users\\mohamed\\Desktop\\xd\\testUpload";
		byte[] bytes = image.getBytes();
		Path path = Paths.get(folder + "1.jpg");
		Files.write(path, bytes);
	}

	public void IamHer() {
		System.out.println("I am Her user operation");
	}
}
