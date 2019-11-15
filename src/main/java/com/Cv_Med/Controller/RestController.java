package com.Cv_Med.Controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.StyledEditorKit.BoldAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Cv_Med.Bean.User_Operatin;
import com.Cv_Med.Hibernet.Competence;
import com.Cv_Med.Hibernet.Cv;
import com.Cv_Med.Hibernet.Formation;
import com.Cv_Med.Hibernet.InfoPersonal;
import com.Cv_Med.Hibernet.Language;
import com.Cv_Med.Hibernet.Leisur;
import com.Cv_Med.Hibernet.User;
import com.Cv_Med.JWT.JwtAuthenticationFilter;
import com.Cv_Med.JsonClasses.CompetenceJson;
import com.Cv_Med.JsonClasses.CvJson;
import com.Cv_Med.JsonClasses.FormationJson;
import com.Cv_Med.JsonClasses.InfoPersonalJson;
import com.Cv_Med.JsonClasses.LanguageJson;
import com.Cv_Med.JsonClasses.LeisureJson;
import com.Cv_Med.JsonClasses.UserJson;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RestController {

	@Autowired
	private User_Operatin user_Operatin;

	@PostMapping("/login")
	public void login() {

	}

	@PostMapping("/registre")
	public boolean registre(HttpServletResponse response, @RequestBody UserJson userJson) {
		response.addHeader("Error", "");
		System.out.println("registre");
		System.out.println("userJson:");
		System.out.println("userJson: " + userJson);
		if (userJson != null) {
			String username = userJson.getUsername();
			String email = userJson.getEmail();
			String password = userJson.getPassword();
			String confirm_password = userJson.getConfirm_Password();

			System.out.println("username " + username);
			System.out.println("email " + email);
			System.out.println("password " + password);
			System.out.println("confirmpassword " + confirm_password);
			Boolean vlemail = validationEamil(email);
			if (!vlemail) {
				response.setHeader("Error", "EmailWrong");
				if (!password.equals(confirm_password)) {
					response.setHeader("Error", "EmailPasswordWrong");

					return false;
				}

				return false;
			}
			if (!password.equals(confirm_password)) {
				response.setHeader("Error", "PasswordWrong");
				return false;
			}
			User user = user_Operatin.registreSecurty(username, email, password, confirm_password);
			if (user != null) {
				System.out.println("registre succesfly");
				return true;
			} else {
				System.out.println("erro on registre");
				response.setHeader("Error", "AccountExiste");
				return false;
			}

		} else {
			System.out.println("user Json is null");
			return false;
		}

	}

	@GetMapping("/logoutJWT")
	public void logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
	}

	@PostMapping("/ListTest")
	public void ListTest(@RequestBody List<UserJson> userJsons) {
		int i = 0;
		for (UserJson userJson : userJsons) {
			i++;
			System.out.println("---------- " + i + " -------------");
			System.out.println(userJson.getUsername());
		}
	}

	@PostMapping(value = "/UploadImage", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public void UplaodImage(HttpServletRequest request, @RequestParam(value = "image") List<MultipartFile> files) {
		System.out.println("------Uplaod Image----------");
		ArrayList<MultipartFile> filesx = new ArrayList<MultipartFile>();
		// filesx.add(files);
		System.out.println("test in headers");
		List<String> FilesName = new ArrayList<String>();
		int i = 0;
		String Key = "";
		String nbr = (String) request.getHeader("NbrHeaders");
		System.out.println("nbr: " + nbr);
		int nbrHeaders = 0;
		if (nbr != null) {
			nbrHeaders = Integer.parseInt((String) request.getHeader("NbrHeaders"));
		}

		System.out.println("nbr of headers is: " + nbrHeaders);
		while (i < nbrHeaders) {
			Key = "FileName" + i;
			System.out.println("key is: " + Key);
			System.out.println("Value is: " + (String) request.getHeader(Key));
			FilesName.add((String) request.getHeader(Key));
			i++;
		}
		System.out.println("testing the output");
		for (String filename : FilesName) {
			System.out.println("file name is: " + filename);
		}
		System.out.println("end testing the output");
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				System.out.println("begun uploiding file");
				String UserName = (String) request.getHeader("UserName");
				String CvId = (String) request.getHeader("CvTitle");
				String FileName = (String) request.getHeader("FileName");
				String TypeProfile = (String) request.getHeader("TypeProfile");
				System.out.println("TypeProogile: " + TypeProfile);
				System.out.println("fetching info in angular local storage username: " + UserName + " CvTitle: " + CvId
						+ " NamFile " + FileName);
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				if (!(auth instanceof AnonymousAuthenticationToken)) {

					/* The user is logged in :) */
					System.out.println("conected");
					System.out.println("Name of user " + auth.getName());

				} else {
					System.out.println("Noconected");
				}
				if (UserName != null && CvId != null && UserName.equals(auth.getName())) {
					user_Operatin.UplaodImage(file, UserName, CvId, TypeProfile, FileName);
					System.out.println("image has been uploaded");
				} else {
					System.out.println("Usernamr or CvId is null");
				}

			} else {
				System.out.println("file is empty");
			}

		}

	}

	@GetMapping(value = "/Getimage/{Repository}/{FileName}")
	public void downloadimage(HttpServletRequest request, HttpServletResponse response, @PathVariable String Repository,
			@PathVariable String FileName) {
		System.out.println("---------------dowloading image from get-----------");
		System.out.println("Repositry: " + Repository);
		System.out.println("FileName: " + FileName);
		String UserName = (String) request.getHeader("UserName");
		String CvTitle = (String) request.getHeader("CvTitle");
		System.out.println("aoturization " + (String) request.getHeader("Authorization"));
		System.out.println("the username in the angular : " + UserName);
		System.out.println("the cv title in the angular: " + CvTitle);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {

			/* The user is logged in :) */
			System.out.println("conected");
			System.out.println("Name of user " + auth.getName());

		} else {
			System.out.println("Noconected");
		}

		if (auth.getName().equals(UserName) && CvTitle != null) {
			try {
				// getting the path to file
				String file1 = "C:" + File.separator + "CvProData" + File.separator + UserName + File.separator
						+ CvTitle + File.separator + Repository;
				System.out.println("filepath: " + file1);
				String fileName = FileName + ".png";
				Path file = Paths.get(file1, fileName);
				if (!Files.exists(file)) {
					System.out.println("file dons't existe");
					String errorMessage = "File you are trying to download does  not exist on the server.";
					OutputStream outputStream = response.getOutputStream();
					outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
					outputStream.close();
					return;
				}
				// getting mimetype from context
				String mimeType = request.getServletContext().getMimeType(file.getFileName().toString());
				if (mimeType == null) {
					// Not able to detect mimetype taking default
					mimeType = "application/octet-stream";
				}
				response.setContentType(mimeType);
				response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
				Files.copy(file, response.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("verfy you cv title or your auhtentication");
		}

	}

	@PostMapping(value = "/CreateNewCv", consumes = { MediaType.APPLICATION_JSON_VALUE }, headers = {
			"Content-Type=application/json" })
	public void CreateNewCv(HttpServletRequest reqeust, HttpServletResponse response, @RequestBody Cv cv) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {

			/* The user is logged in :) */
			System.out.println("conected");
			System.out.println("Name of user " + auth.getName());

		} else {
			System.out.println("Noconected");
		}
		String UserName = (String) reqeust.getHeader("UserName");
		System.out.println("the username in the Locale Storage of Angular is: " + UserName);
		if (UserName.equals(auth.getName())) {
			cv = user_Operatin.createCv(auth.getName(), cv);
			if (cv != null) {
				System.out.println("cv has been created succesfuly");
				String CvId = Integer.toString(cv.getId());
				response.setHeader("CvId", CvId);
			} else {
				System.out.println("problem cv hasn't be created");
				return;
			}

		} else {
			System.out.println("Problem with authentication");
		}

	}

	@PostMapping(value = "/UpdateCv", consumes = { MediaType.APPLICATION_JSON_VALUE }, headers = {
			"Content-Type=application/json" })
	public void UpdateCv(HttpServletRequest reqeust, HttpServletResponse response, @RequestBody List<Cv> cvs) {
		System.out.println("----------Update Cv----------");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {

			/* The user is logged in :) */
			System.out.println("conected");
			System.out.println("Name of user " + auth.getName());

		} else {
			System.out.println("Noconected");
		}

		System.out.println("title of cvs " + cvs.get(0).getTitle());
		System.out.println("title of cvs " + cvs.get(1).getTitle());
		String UserName = (String) reqeust.getHeader("UserName");
		System.out.println("the username in the Locale Storage of Angular is: " + UserName);
		if (UserName.equals(auth.getName())) {
			Cv cv = user_Operatin.updateCv(auth.getName(), cvs);
			if (cv != null) {
				String CvId = Integer.toString(cv.getId());
				response.setHeader("CvId", CvId);
				System.out.println("cv has been created succesfuly");
			} else {
				System.out.println("problem cv hasn't be created");
				return;
			}

		} else {
			System.out.println("Problem with authentication");
		}
		System.out.println("----------end Update Cv----------");

	}

	@PostMapping(value = "/DeleteCv", consumes = { MediaType.APPLICATION_JSON_VALUE }, headers = {
			"Content-Type=application/json" })
	public void DeleteCv(HttpServletRequest reqeust, HttpServletResponse response, @RequestBody Cv cv) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {

			/* The user is logged in :) */
			System.out.println("conected");
			System.out.println("Name of user " + auth.getName());

		} else {
			System.out.println("Noconected");
		}
		String UserName = (String) reqeust.getHeader("UserName");
		System.out.println("the username in the Locale Storage of Angular is: " + UserName);
		if (UserName.equals(auth.getName())) {
			System.out.println("cv name is "+cv.getTitle());
			boolean key = user_Operatin.deleteCv(auth.getName(), cv);
			if(key) {
				System.out.println("cv has been deleted successfly");
			}else {
				System.out.println("Error deleting cv");
				return;
			}

		} else {
			System.out.println("Problem with authentication");
		}

	}

	@GetMapping(value = "/ShowCv")
	public List<CvJson> ShowCv(HttpServletRequest reqeust) {
		System.out.println("----------Show Cv----------");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {

			/* The user is logged in :) */
			System.out.println("conected");
			System.out.println("Name of user " + auth.getName());

		} else {
			System.out.println("Noconected");
		}
		String UserName = (String) reqeust.getHeader("UserName");
		System.out.println("the username in the Locale Storage of Angular is: " + UserName);
		if (UserName.equals(auth.getName())) {
			List<CvJson> cvs = user_Operatin.showCv(auth.getName());
			if (cvs != null) {
				System.out.println("cvs have been generated succesfuly");
				System.out.println("----------end Show Cv----------");
				int i = 0;
				for (CvJson cv : cvs) {
					System.out.println("cv index " + i + " title " + cv.getTitle());
					i++;
				}
				return cvs;
			} else {
				System.out.println("problem cvs haven't be generated");
				System.out.println("----------end Show Cv----------");
				return null;
			}

		} else {
			System.out.println("Problem with authentication");
			System.out.println("----------end Show Cv----------");
			return null;
		}

	}

	@PostMapping(value = "/CreateNewFormation", consumes = { MediaType.APPLICATION_JSON_VALUE }, headers = {
			"Content-Type=application/json" })
	public void CreateNewFomration(HttpServletRequest request, @RequestBody List<Formation> formations) {
		for (Formation formation : formations) {
			System.out.println("yearsBegun: " + formation.getYearsBegun());
			System.out.println("formation: " + formation.getFormation());
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {

			/* The user is logged in :) */
			System.out.println("conected");
			System.out.println("Name of user " + auth.getName());

		} else {
			System.out.println("Noconected");
		}

		String UserName = (String) request.getHeader("UserName");
		String CvTitle = (String) request.getHeader("CvTitle");
		System.out.println("the username in the angular : " + UserName);
		System.out.println("the cv title in the angular: " + CvTitle);
		if (auth.getName().equals(UserName) && CvTitle != null) {
			user_Operatin.createFormation(UserName, CvTitle, formations);
			System.out.println("Formations have been created succesfly");
		} else {
			System.out.println("verfy you cv title or your auhtentication");
		}

	}

	@PostMapping(value = "/CreateNewInfoPersonal", consumes = { MediaType.APPLICATION_JSON_VALUE }, headers = {
			"Content-Type=application/json" })
	public void CreateNewInfoPersonal(HttpServletRequest request, @RequestBody InfoPersonal info) {
		System.out.print("-------------Posting Personal Info----------------------");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {

			/* The user is logged in :) */
			System.out.println("conected");
			System.out.println("Name of user " + auth.getName());

		} else {
			System.out.println("Noconected");
		}
		String UserName = (String) request.getHeader("UserName");
		String CvTitle = (String) request.getHeader("CvTitle");
		System.out.println("the username in the sessionis: " + UserName);
		System.out.println("the cv title in the sessionis: " + CvTitle);
		if (auth.getName().equals(UserName) && CvTitle != null) {
			user_Operatin.createInfoPersonal(UserName, CvTitle, info);
			System.out.println("InfoPersonal have been created succesfly");
		} else {
			System.out.println("verfy you cv title or your auhtentication");
		}
		System.out.print("-------------end Posting Personal Info----------------------");
	}

	@PostMapping(value = "/CreateNewCompetence", consumes = { MediaType.APPLICATION_JSON_VALUE }, headers = {
			"Content-Type=application/json" })
	public void CreateNewCompetence(HttpServletRequest request, @RequestBody List<Competence> competences) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {

			/* The user is logged in :) */
			System.out.println("conected");
			System.out.println("Name of user " + auth.getName());

		} else {
			System.out.println("Noconected");
		}
		String UserName = (String) request.getHeader("UserName");
		String CvTitle = (String) request.getHeader("CvTitle");
		System.out.println("the username in the angular : " + UserName);
		System.out.println("the cv title in the angular: " + CvTitle);
		if (auth.getName().equals(UserName) && CvTitle != null) {
			user_Operatin.createCompetence(UserName, CvTitle, competences);
			System.out.println("Formations have been created succesfly");
		} else {
			System.out.println("verfy you cv title or your auhtentication");
		}

	}

	@PostMapping(value = "/CreateNewLanguage", consumes = { MediaType.APPLICATION_JSON_VALUE }, headers = {
			"Content-Type=application/json" })
	public void CreateNewLanguage(HttpServletRequest request, @RequestBody List<Language> languages) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {

			/* The user is logged in :) */
			System.out.println("conected");
			System.out.println("Name of user " + auth.getName());

		} else {
			System.out.println("Noconected");
		}
		String UserName = (String) request.getHeader("UserName");
		String CvTitle = (String) request.getHeader("CvTitle");
		System.out.println("the username in the angular : " + UserName);
		System.out.println("the cv title in the angular: " + CvTitle);
		if (auth.getName().equals(UserName) && CvTitle != null) {
			user_Operatin.createLanguages(UserName, CvTitle, languages);
			System.out.println("Formations have been created succesfly");
		} else {
			System.out.println("verfy you cv title or your auhtentication");
		}

	}

	@PostMapping(value = "/CreateNewLeisure", consumes = { MediaType.APPLICATION_JSON_VALUE }, headers = {
			"Content-Type=application/json" })
	public void CreateNewLeisure(HttpServletRequest request, @RequestBody List<Leisur> leisures) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {

			/* The user is logged in :) */
			System.out.println("conected");
			System.out.println("Name of user " + auth.getName());

		} else {
			System.out.println("Noconected");
		}
		String UserName = (String) request.getHeader("UserName");
		String CvTitle = (String) request.getHeader("CvTitle");
		System.out.println("the username in the angular : " + UserName);
		System.out.println("the cv title in the angular: " + CvTitle);
		if (auth.getName().equals(UserName) && CvTitle != null) {
			user_Operatin.createLeisur(UserName, CvTitle, leisures);
			System.out.println("Formations have been created succesfly");
		} else {
			System.out.println("verfy you cv title or your auhtentication");
		}

	}


	@GetMapping(value = "/ShowFormation")
	public List<FormationJson> ShowFomration(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("::::::::Show Formation:::::::::::::");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {

			/* The user is logged in :) */
			System.out.println("conected");
			System.out.println("Name of user " + auth.getName());

		} else {
			System.out.println("Noconected");
		}

		String UserName = (String) request.getHeader("UserName");
		String CvTitle = (String) request.getHeader("CvTitle");
		System.out.println("the username in the angular : " + UserName);
		System.out.println("the cv title in the angular: " + CvTitle);
		if (auth.getName().equals(UserName) && CvTitle != null) {
			System.out.println("Formations have been created succesfly");
			return user_Operatin.showFormation(UserName, CvTitle) ;
			
		} else {
			System.out.println("verfy you cv title or your auhtentication");
			return null;
		}

	}

	@GetMapping(value = "/ShowInfoPersonal")
	public InfoPersonalJson ShowInfoPersonal(HttpServletRequest request) {
		System.out.print("-------------Posting Personal Info----------------------");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {

			/* The user is logged in :) */
			System.out.println("conected");
			System.out.println("Name of user " + auth.getName());

		} else {
			System.out.println("Noconected");
		}
		String UserName = (String) request.getHeader("UserName");
		String CvTitle = (String) request.getHeader("CvTitle");
		System.out.println("the username in the sessionis: " + UserName);
		System.out.println("the cv title in the sessionis: " + CvTitle);
		if (auth.getName().equals(UserName) && CvTitle != null) {
			System.out.println("InfoPersonal have been created succesfly");
			return user_Operatin.showInfoPersonal(UserName, CvTitle);
		} else {
			System.out.println("verfy you cv title or your auhtentication");
			return null;
		}
		
	}

	@GetMapping(value = "/ShowCompetence")
	public List<CompetenceJson> ShowCompetence(HttpServletRequest request) {
		System.out.println("::::::::::::::showCompetence:::::::::");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {

			/* The user is logged in :) */
			System.out.println("conected");
			System.out.println("Name of user " + auth.getName());

		} else {
			System.out.println("Noconected");
		}
		String UserName = (String) request.getHeader("UserName");
		String CvTitle = (String) request.getHeader("CvTitle");
		System.out.println("the username in the angular : " + UserName);
		System.out.println("the cv title in the angular: " + CvTitle);
		if (auth.getName().equals(UserName) && CvTitle != null) {
			System.out.println("Formations have been created succesfly");
			return user_Operatin.showCompetence(UserName, CvTitle);
		} else {
			System.out.println("verfy you cv title or your auhtentication");
			return null;
		}

	}

	@GetMapping(value = "/ShowLanguage")
	public List<LanguageJson> ShowLanguage(HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {

			/* The user is logged in :) */
			System.out.println("conected");
			System.out.println("Name of user " + auth.getName());

		} else {
			System.out.println("Noconected");
		}
		String UserName = (String) request.getHeader("UserName");
		String CvTitle = (String) request.getHeader("CvTitle");
		System.out.println("the username in the angular : " + UserName);
		System.out.println("the cv title in the angular: " + CvTitle);
		if (auth.getName().equals(UserName) && CvTitle != null) {
			
			System.out.println("Formations have been created succesfly");
			return user_Operatin.showLanguages(UserName, CvTitle);
		} else {
			System.out.println("verfy you cv title or your auhtentication");
			return null;
		}

	}

	@GetMapping(value = "/ShowLeisure")
	public List<LeisureJson> ShowLeisure(HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {

			/* The user is logged in :) */
			System.out.println("conected");
			System.out.println("Name of user " + auth.getName());

		} else {
			System.out.println("Noconected");
		}
		String UserName = (String) request.getHeader("UserName");
		String CvTitle = (String) request.getHeader("CvTitle");
		System.out.println("the username in the angular : " + UserName);
		System.out.println("the cv title in the angular: " + CvTitle);
		if (auth.getName().equals(UserName) && CvTitle != null) {
			System.out.println("Formations have been created succesfly");
			return user_Operatin.showLeisur(UserName, CvTitle);
		} else {
			System.out.println("verfy you cv title or your auhtentication");
			return null;
		}

	}

	
	
	private Boolean validationEamil(String email) {
		String[] emailArray = email.split("@");
		int i = 0;
		while (i < emailArray.length) {
			System.out.println(i + " : " + emailArray[i]);
			i++;
			// System.out.println(emailArray[emailArray.length-10,emailArray.length-1]);
		}
		if (emailArray.length == 2) {
			if (emailArray[1].equals("gmail.com")) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

}
