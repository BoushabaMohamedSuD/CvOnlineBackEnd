package com.Cv_Med.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.Cv_Med.Bean.GetContainer;
import com.Cv_Med.Bean.User_Operatin;
import com.Cv_Med.Configue.ConfigueBean;
import com.Cv_Med.Configue.Configue_BeanTest;
import com.Cv_Med.Configue.Configue_Spring;
import com.Cv_Med.Hibernet.User;
import com.Cv_Med.Service.ServiceDaoImplimentation;
import com.Cv_Med.Test_Bean.BeanAutowiredTest;
import com.Cv_Med.Test_Bean.Dependency1;
import com.Cv_Med.Test_Bean.Dependency2;
import com.Cv_Med.Test_Bean.Test_Bean1;
import com.Cv_Med.Test_Bean.Test_Bean2;
import com.Cv_Med.Test_Bean.Test_Bean3;
import com.Cv_Med.Test_Bean.test_Scope;
import com.Cv_Med.Test_Hibernet.Student;
import com.Cv_Med.Test_Hibernet.StudentDao;
import com.Cv_Med.Test_Hibernet.StudentimplementDao;
import com.Cv_Med.Test_Jdbc.Test_Connectivity;
import com.mysql.cj.conf.PropertyDefinitions.AuthMech;

@Controller
public class Controller_Cv {

	// this is just for the test it's not practical at all
	// let's start
	@Autowired
	@Qualifier("getContainer")
	private GetContainer getContainer;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	
	

	// test done you have to remove it
	// becaus you will have problem of dependencies
	// becaus anotati...... class it's still not a bean
	// big problem
	// be careful
	// stupid
	@Autowired
	private User_Operatin user_Operatin;

	// those proprietise are for views
	private String ViewIndex = "index/index";
	private String ViewContactUs = "contact_us/contact_us";
	private String ViewCv = "cv/cv";
	private String ViewCvExemple = "cv_exemple/cv_exemlpe";
	private String ViewDetailProject = "detail_project/detail_project";
	private String ViewExport = "export/export";
	private String ViewLogin = "login/Login";
	private String ViewLogout = "logout/LogOUT";
	private String ViewProfile = "profile/profile";
	private String ViewStyle1 = "style1/style1";

	@GetMapping("/")
	public String show_welcome() {

		return ViewIndex;
	}

	@GetMapping("/index")
	public String show_index(HttpServletRequest request, Model model) {
		
		return ViewIndex;
	}

	@GetMapping("/contact_us")
	public String show_contact_us() {

		return ViewContactUs;
	}

	@GetMapping("/cv")
	public String show_cv(HttpServletRequest request, Model model) {
		String view = ViewCv;
		// view = oldConfigueWithoutSpring(ViewCv, request);
		return view;

	}

	@GetMapping("/cv_exemple")
	public String show_cv_exemple(HttpServletRequest request, Model model) {
		String view = ViewCvExemple;
		// view = oldConfigueWithoutSpring(ViewCvExemple, request);
		return view;

	}

	@GetMapping("/detail_project")
	public String show_detail_project(HttpServletRequest request, Model model) {
		String view = ViewDetailProject;
		// view = oldConfigueWithoutSpring(ViewDetailProject, request);
		return view;

	}

	@GetMapping("/export")
	public String show_export(HttpServletRequest request, Model model) {
		String view = ViewExport;
		// view = oldConfigueWithoutSpring(ViewExport, request);
		return view;

	}

	@GetMapping("/loginFormJsp")
	public String show_login(HttpServletRequest request, Model model) {
		System.out.println("là");
		String view = ViewLogin;
		// view = oldConfigueWithoutSpringLoginGet(ViewLogin, request);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken)) {

			/* The user is logged in :) */
			System.out.println("conected");
			return "forward:/logout";
		}
		System.out.println("Noconected");
		return view;

	}

	@GetMapping("/logout")
	public String show_logout(HttpServletRequest request, Model model) {
		String view = ViewLogout;
		// view = oldConfigueWithoutSpring(ViewLogout, request);

		return view;

	}

	@GetMapping("/profile")
	public String show_profile(HttpServletRequest request, Model model) {
		String view = ViewProfile;
		// view = oldConfigueWithoutSpring(ViewProfile, request);
		return view;

	}

	@GetMapping("/style1")
	public String show_style1(HttpServletRequest request, Model model) {
		String view = ViewStyle1;
		// view = oldConfigueWithoutSpring(ViewStyle1, request);
		return view;

	}

	// !!!!!!!!!!!!!!!!!!!!!!!!for
	// post!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

	@PostMapping("/")
	public String show_welcome_Post(HttpServletRequest request, Model model) {
		System.out.println("post");
		return "index/index";
	}

	@PostMapping("/index")
	public String show_index_Post(HttpServletRequest request, Model model) {
		
		return "index/index";
	}

	@PostMapping("/contact_us")
	public String show_contact_us_Post(HttpServletRequest request, Model model) {

		return "contact_us/contact_us";
	}

	@PostMapping("/cv")
	public String show_cv_Post(HttpServletRequest request, Model model) {
		String view = ViewCv;
		// view = oldConfigueWithoutSpring(ViewCv, request);
		return view;

	}

	@PostMapping("/cv_exemple")
	public String show_cv_exemple_Post(HttpServletRequest request, Model model) {
		String view = ViewCvExemple;
		// view = oldConfigueWithoutSpring(ViewCvExemple, request);
		return view;

	}

	@PostMapping("/detail_project")
	public String show_detail_project_Post(HttpServletRequest request, Model model) {
		String view = ViewDetailProject;
		// view = oldConfigueWithoutSpring(ViewDetailProject, request);
		return view;

	}

	@PostMapping("/export")
	public String show_export_Post(HttpServletRequest request, Model model) {
		String view = ViewExport;
		// view = oldConfigueWithoutSpring(ViewExport, request);
		return view;

	}

	@PostMapping("/Login")
	public String show_login_Post(HttpServletRequest request, Model model) {
		String view = ViewLogin;
		// view = oldConfigueWithoutSpringLoginPost(ViewLogin, request);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = (String) request.getParameter("username");
		String email = (String) request.getParameter("email");
		String password = (String) request.getParameter("password");
		String confirm_password = (String) request.getParameter("confirm_password");
			
		System.out.println("pooooost!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

		if (!(auth instanceof AnonymousAuthenticationToken)) {

			/* The user is logged in :) */
			return "forward:/logout";
		} else if (email != null) {
			System.out.println("registre");
			User user = user_Operatin.registreSecurty(username, email, password, confirm_password);
			if (user != null) {
				System.out.println("true");
				model.addAttribute("username",username);
				model.addAttribute("password",password);
				//return "login/Login";
				//manual authentication with spring securty
				loginauto(request,username,password);
				return "index/index";
				
			
			} else {
				System.out.println("false");
				return "login/Login";
			}

		}
		return view;

	}

	@PostMapping("/logout")
	public String show_logout_Post(HttpServletRequest request, Model model) {
		String view = ViewLogout;
		// view = oldConfigueWithoutSpringLogoutPost(ViewLogout, request);
		return view;

	}

	@PostMapping("/profile")
	public String show_profile_Post(HttpServletRequest request, Model model) {
		String view = ViewProfile;
		// view = oldConfigueWithoutSpring(ViewProfile, request);
		return view;

	}

	@PostMapping("/style1")
	public String show_style1_Post(HttpServletRequest request, Model model) {
		String view = ViewStyle1;
		// view = oldConfigueWithoutSpring(ViewStyle1, request);
		return view;

	}

	// old confique!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	// this methode is for the configuration
	// befor adding spring securty

	public String oldConfigueWithoutSpring(String view, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer id = (Integer) session.getAttribute("Id");
		if (id != null) {
			return view;

		} else {
			return "redirect:/";
		}

	}

	public String oldConfigueWithoutSpringLoginPost(String view, HttpServletRequest request) {
		System.out.println("post");
		String pseudo = (String) request.getParameter("pseudo");
		String email = (String) request.getParameter("email");
		String password = (String) request.getParameter("password");
		String confirm_password = (String) request.getParameter("confirm_password");
		HttpSession session = request.getSession();
		Integer id = (Integer) session.getAttribute("Id");
		if (id == null) {

			if (confirm_password != null) {
				System.out.println("registre");
				User user = user_Operatin.registre(pseudo, email, password, confirm_password);
				if (user != null) {

					session.setAttribute("Id", user.getId());
					System.out.println("true");
					return "redirect:/";
				} else {
					System.out.println("false");
					return "login/Login";
				}
			} else {
				System.out.println("login");
				User user = user_Operatin.login(pseudo, email, password);

				if (user != null) {

					session.setAttribute("Id", user.getId());
					System.out.println("true");
					return "redirect:/";
				} else {
					System.out.println("false");
					return "login/Login";
				}

			}

		} else {
			return "redirect:/logout";
		}

	}

	public String oldConfigueWithoutSpringLoginGet(String view, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer id = (Integer) session.getAttribute("Id");
		if (id == null) {
			return view;

		} else {
			return "redirect:/logout";
		}
	}

	public String oldConfigueWithoutSpringLogoutPost(String view, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer id = (Integer) session.getAttribute("Id");

		if (id != null) {
			user_Operatin.logout(id);
			session.removeAttribute("Id");
			return "redirect:/login";

		} else {
			return "redirect:/";
		}

	}

	// for the test!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

	@RequestMapping("/Test")
	public String showWelcomeTest(HttpServletRequest request, Model model) {

		AnnotationConfigApplicationContext context = getContainer.getContextTest();
		Test_Bean1 ts1 = context.getBean("test_Bean1", Test_Bean1.class);
		Test_Bean1 ts2 = context.getBean("test_Bean1", Test_Bean1.class);
		ts1.lets_play();
		ts2.lets_play();
		// the conclusion is that ts1 and ts2 are the same thats mean ts1 and ts2 are
		// the same instance

		// other test
		Test_Bean1 ts3 = context.getBean("test_Bean1_1", Test_Bean1.class);
		ts3.lets_play();

		// after this we conclud that ts3 is deferent then ts2 and ts1
		Test_Bean1 ts4 = context.getBean("test_Bean1_2", Test_Bean1.class);
		ts4.setDependency1(new Dependency1());
		ts4.lets_play();

		// conclusion the dependency has been accepted but we need more to conclide

		// !!!!!!!!! if we add those codes they make an error
		/*
		 * Test_Bean1 ts5=context.getBean("test_Bean1_3",Test_Bean1.class);
		 * ts5.setDependency1(new Dependency1()); ts5.lets_play();
		 */
		// another test
		Test_Bean2 ts2_1 = context.getBean("test_Bean2", Test_Bean2.class);
		ts2_1.lets_play();
		Dependency1 db1 = context.getBean("dependency1", Dependency1.class);
		db1.change(15);
		ts2_1.lets_play();
		db1.change(18);
		ts2_1.lets_play();
		Test_Bean2 ts2_2 = context.getBean("test_Bean2_1", Test_Bean2.class);
		ts2_2.lets_play();

		Test_Bean2 ts2_3 = context.getBean("test_Bean2_3", Test_Bean2.class);
		ts2_3.lets_play();

		// test bean 3
		Test_Bean3 ts3_1 = context.getBean("test_Bean3_1", Test_Bean3.class);
		ts3_1.letsplay();
		Test_Bean3 ts3_2 = context.getBean("test_Bean3_2", Test_Bean3.class);
		ts3_2.letsplay();
		Dependency2 db2_1 = context.getBean("dependency2_1", Dependency2.class);
		Dependency2 db2_2 = context.getBean("dependency2_2", Dependency2.class);
		db2_1.setchange(12);
		db2_2.setchange(0);
		db2_1.setint(2, 3);
		db2_2.setint(1, 5);
		ts3_2.letsplay();

		// so waht i conclud in the last that spring go to the class of configue and
		// take an instances of classes
		// and make it as the ticket and tha's the same with the in dependency

		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// !!!!!!!! @aitowired used in the xml confisue not her
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// testing scope protoype <!!! the default is singleton

		test_Scope scope1 = context.getBean("test_scope", test_Scope.class);
		scope1.letsplay();
		scope1.letsplay();
		test_Scope scope2 = context.getBean("test_scope", test_Scope.class);
		scope2.letsplay();
		if (scope1 == scope2) {
			System.out.print("that is not a prototype");
		} else {
			System.out.println("yeahhhh");
		}
		// testing scope with dependecy

		test_Scope scope3 = context.getBean("test_scope1", test_Scope.class);
		scope3.letsplay();
		scope3.letsgo();
		test_Scope scope4 = context.getBean("test_scope1", test_Scope.class);
		scope4.letsplay();
		scope4.letsgo();
		if (scope3.getdbs() == scope4.getdbs()) {
			System.out.print("that is not a prototype for dependency");
		} else {
			System.out.println("yeahhhh the dependencies are also the same");
		}

		// conclusion
		// we need to define the scope of the dependency also

		// done

		// testing the connection to jdbc
		// befor adding hiberneti.xml

		/*
		 * Test_Connectivity
		 * myconn=context.getBean("test_connection",Test_Connectivity.class);
		 * myconn.conection();
		 */

		// for dao the default way
		StudentDao dao = new StudentimplementDao();
		Student student1 = new Student("mlk", "hfjdl@gmail.com", "mlk");
		Student student2 = new Student("ghghj", "ppul@gmail.com", "mryyu");
		Student student3 = new Student("plj", "ljujdl@gmail.com", "mktrk");
		dao.add_Student(student1);
		dao.add_Student(student2);
		dao.add_Student(student3);

		// test the reposotry dao as bean and define a scope
		StudentimplementDao dao1 = (StudentimplementDao) context.getBean("studentdao", StudentDao.class);
		student1 = new Student("mlkx", "xhfjdl@gmail.com", "mlk");
		dao1.add_Student(student1);
		dao1.letplay();

		StudentimplementDao dao2 = (StudentimplementDao) context.getBean("studentdao", StudentDao.class);
		dao2.remove_Student(student1.getId());
		dao2.letplay();
		if (dao1 == dao2) {
			System.out.print("that is not a prototype on reposotry");
		} else {
			System.out.println("yeahhhh prototype on reposotry");
		}

		return ViewIndex;
	}

	@RequestMapping("/Test1")
	public String showWelcomeTest1() {

		BeanAutowiredTest bat = getContainer.getContextTest().getBean("beanAutowiredTest", BeanAutowiredTest.class);
		bat.letsplay();

		return ViewIndex;

	}
	
	
	private void loginauto(HttpServletRequest request,String username,String password) {
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
			
		}else {
			System.out.println("NOauto");
		}
		
	}
	

}
