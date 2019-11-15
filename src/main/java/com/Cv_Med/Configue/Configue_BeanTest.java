package com.Cv_Med.Configue;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.Cv_Med.Tesr_.Aop.Test_befor_letsplay;
import com.Cv_Med.Test_Bean.BeanAutowiredTest;
import com.Cv_Med.Test_Bean.Dependency1;
import com.Cv_Med.Test_Bean.Dependency2;
import com.Cv_Med.Test_Bean.DependencyAuowiredTest;
import com.Cv_Med.Test_Bean.DependencyScope;
import com.Cv_Med.Test_Bean.Test_Bean1;
import com.Cv_Med.Test_Bean.Test_Bean2;
import com.Cv_Med.Test_Bean.Test_Bean3;
import com.Cv_Med.Test_Bean.test_Scope;
import com.Cv_Med.Test_Hibernet.StudentDao;
import com.Cv_Med.Test_Hibernet.StudentimplementDao;
import com.Cv_Med.Test_Jdbc.Test_Connectivity;

@Configuration
@EnableAspectJAutoProxy
public class Configue_BeanTest {

	//for the test and learning!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	@Bean
	public Test_Bean1 test_Bean1() {
		Test_Bean1 ts=new Test_Bean1();
		return ts;
	}
	@Bean
	public Test_Bean1 test_Bean1_1() {
		Test_Bean1 ts=new Test_Bean1();
		return ts;
	}
	@Bean
	public Test_Bean1 test_Bean1_2() {
		Test_Bean1 ts=new Test_Bean1();
		return ts;
	}
	@Bean
	public Test_Bean1 test_Bean1_3() {
		Test_Bean1 ts=new Test_Bean1();
		return ts;
	}
	
	
	//dependency
	@Bean
	public Dependency1 dependency1() {
		return new Dependency1();
	}
	@Bean
	public Dependency1 dependency1_1() {
		return new Dependency1();
	}
	

	@Bean
	public Test_Bean2 test_Bean2() {
		
		return new Test_Bean2(dependency1());
	}
	@Bean
	public Test_Bean2 test_Bean2_1() {
		
		return new Test_Bean2(dependency1());
	}
	@Bean
	public Test_Bean2 test_Bean2_3() {
		
		return new Test_Bean2(dependency1_1());
	}
	
	
	//test bean 3
	@Bean
	public Dependency2 dependency2_1() {
		return new Dependency2();
	}
	
	
	@Bean
	public Dependency2 dependency2_2() {
		return new Dependency2();
	}
	
	@Bean
	public Test_Bean3 test_Bean3_1() {
		
		return new Test_Bean3(dependency2_1(),dependency2_2());
	}
	@Bean
	public Test_Bean3 test_Bean3_2() {
		
		Test_Bean3 ts= new Test_Bean3();
		ts.setdep(dependency2_1(), dependency2_2());
		return ts;
	}
	
	//for testing scope
	
	@Bean
	@Scope("prototype")
	public test_Scope test_scope() {
		
		test_Scope ts= new test_Scope();
		
		return ts;
	}

	@Bean
	@Scope("prototype")
	public test_Scope test_scope1() {
		
		test_Scope ts= new test_Scope(dependencyScope());
		
		return ts;
	}
	@Bean
	@Scope("prototype")
	public DependencyScope dependencyScope() {
		
		return new DependencyScope();
	}
	
	//for mysql satabase
	
	@Bean
	@Scope("prototype")
	public Test_Connectivity test_connection() {
		
		return new Test_Connectivity();
	}
	
	
	//test for dao as ab ean
	@Bean
	@Scope("prototype")
	public StudentDao studentdao() {
		
		return new StudentimplementDao();
	}
	
	//for aspect
	@Bean
	@Scope("prototype")
	public Test_befor_letsplay test_befor_letsplay() {
		
		return new Test_befor_letsplay();
	}
	
	//for autowired
	@Bean
	@Scope("prototype")
	public BeanAutowiredTest beanAutowiredTest() {
		
		return new BeanAutowiredTest();
		
		
	}
	@Bean
	public DependencyAuowiredTest dependencyAutowiredTest() {
		
		return new DependencyAuowiredTest();
		
		
	}
	
	
	
	
	
	//done !!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	

}
