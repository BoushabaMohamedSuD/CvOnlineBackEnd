package com.Cv_Med.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.aspectj.weaver.ast.Test;
import org.hibernate.loader.plan.spi.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.Cv_Med.Bean.User_Operatin;
import com.Cv_Med.Hibernet.Cv;
import com.Cv_Med.Hibernet.Formation;
import com.Cv_Med.Hibernet.User;
import com.Cv_Med.Hibernet.UserDaoImplimentation;
import com.Cv_Med.JsonClasses.UserJson;
import com.Cv_Med.JsonClasses.UserTestJson;
import com.Cv_Med.Service.ServiceDaoImplimentation;
import com.Cv_Med.Test_Hibernet.CvTest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin
public class RestControllerTest {

	// testin login process url!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! for spring
	// securty
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	private ServiceDaoImplimentation serviceDaoImplimentation;
	@Autowired
	private UserDaoImplimentation userDaoImplimentation;
	@Autowired
	private User_Operatin user_Operatin;

	private static int i = 0;

	// what we understand her we can change the login in configuration
	// by anything url we want like /loginRest
	// so how spring securty work
	// simple
	// when you load for this example the login registre
	// spring securty wait to send username and password
	// login processs url by post
	// and the rest are handling by spring securty
	@PostMapping("/loginProcess1")
	public String showLoginUrl(HttpServletRequest request) {
		System.out.println("login process url xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		System.out.println(request.getAttribute("username"));
		System.out.println(i);
		i++;
		String a = "hhhh";
		return a;

	}

	@PostMapping("/loginAngular")
	public void login() {
		System.out.println("login froma angular");

	}

	@GetMapping("/name")
	public String name() {
		String a = "Mohamed";

		return a;

	}

	@GetMapping("/user1")
	public UserTestJson user() {
		// UserTestJson user=new UserTestJson();
		/*
		 * UserTestJson user = new UserTestJson(); user.setEmail("med1998yz@gmail.com");
		 * user.setPseudo("Mohamed"); return user;
		 */
		return null;

	}

	@PostMapping("/post")
	public void PostTestJson(@RequestBody UserTestJson user) {
		System.out.println("Post Json Test");
		System.out.println(user.getEmail() + " " + user.getPseudo());
	}

	@PostMapping("/addcvtest")
	public void addcv(@RequestBody UserJson userJson) {
		System.out.println("add cv begun");
		Cv cv = new Cv();
		System.out.println(userJson.getUsername());
		User user = serviceDaoImplimentation.addCvJson(userJson, cv);
		System.out.println(user.getCv());
		for (Cv cvp : user.getCv()) {
			System.out.println("-------------------");
			System.out.println(cvp.getId());
			System.out.println(cvp.getUser().getPseudo());
		}
	}

	@PostMapping("/addformationtest")
	public void addformation() {
		System.out.println("-------------------");
		Formation formation = new Formation();
		serviceDaoImplimentation.addformationtest(formation);

	}

	@PostMapping(value="/begunUpload",consumes={MediaType.MULTIPART_FORM_DATA_VALUE},headers= {"Content-Type=multipart/form-data"})
	public void addimage(@RequestParam("image") MultipartFile file) {
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
				String fileName = "C:" + File.separator + "hello" + File.separator + "hi.PNG";
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

	// Handler Method for multiple file uploads
	@PostMapping(value = "/uploadMultiFile", headers = "content-type=multipart/*")
	@ResponseBody
	public String uploadMultipleFiles(@RequestParam("image") MultipartFile[] files) {
		System.out.println("---------Upload----------");
		String msg = "";
		int emptyCount = 0;
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				BufferedOutputStream bos = null;
				try {
					byte[] fileBytes = file.getBytes();
					System.out.print(file.getOriginalFilename());
					String fileName = "C:" + File.separator + "hello" + File.separator + "hi.PNG";
					System.out.println(fileName);
					File f = new File(fileName);
					System.out.println(f.getFreeSpace());
					bos = new BufferedOutputStream(new FileOutputStream(f));
					bos.write(fileBytes);
					msg = msg + "Upload successful for " + file.getOriginalFilename() + "<br />";
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					if (bos != null) {
						try {
							bos.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			} else {
				emptyCount++;

			}
		}
		// Equal means no file is selected for upload
		if (files.length == emptyCount) {
			msg = "Upload failed as no file is selected";
		}
		return msg;
	}

	@GetMapping("/downlaodimage")
	public void downloadimage(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("dowloading image");
		try {
			// getting the path to file
			String file1 = "C:" + File.separator + "hello";
			String fileName = "hi.PNG";
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
	}

	@GetMapping("/getimagetest")
	public File getimage() {
		String fileName = "C:" + File.separator + "hello" + File.separator + "hi.PNG";
		System.out.println(fileName);
		File f = new File(fileName);
		return f;
	}

	@GetMapping("/filetest")
	public void createfile() {
		System.out.println("getimage");
		String path = "C:" + File.separator + "hello" + File.separator + "hi.txt";
		// Use relative path for Unix systems
		File f = new File(path);

		f.getParentFile().mkdirs();
		try {
			f.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void loginauto(HttpServletRequest request, String username, String password) {
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

		// Authenticate the user
		Authentication authentication = authenticationManager.authenticate(authRequest);
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);

		// Create a new session and add the security context.
		HttpSession session = ((HttpServletRequest) request).getSession(true);
		session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
		System.out.println("autoLogin");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {

			/* The user is logged in :) */
			System.out.println("auto");

		} else {
			System.out.println("NOauto");
		}

	}

	private User JsonPojo(String urlJason) throws JsonParseException, JsonMappingException, IOException {
		// String urlJason = null;

		ObjectMapper mapper = new ObjectMapper();
		/*
		 * User user=new User(); user.setPseudo("Mohamed");
		 * user.setEmail("med1998yz@gmail.com");
		 */
		User user = mapper.readValue(urlJason, User.class);
		return user;

	}

	private void PojoJson() {

	}
}
