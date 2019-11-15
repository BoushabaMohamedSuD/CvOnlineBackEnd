package com.Cv_Med.Configue;


import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.Cv_Med.Hibernet.User;
import com.Cv_Med.Hibernet.UserDaoImplimentation;

import javax.sql.DataSource;
import java.util.Properties;

//when we use this class we don't need
//the hibernate.cfg.xml
//and this case is much better because 
//we solve the problem for 
//@autowired a seassion factory
//and we solve the problem of @transaction
//because as we know 
//the seesion factory is an object
//so for injected on a bean
//we need to configured 
//and this class do evrying for you
//so be happy
//check the code to understande weel 
//what i wanna mean have a nice day

@Configuration
@EnableTransactionManagement


public class Confique_Hibernit {
	private String Driver ="com.mysql.cj.jdbc.Driver";
	private String Url="jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false&amp;serverTimezone=UTC";
	private String Username="hbstudent";
	private String Password="hbstudent";
	private String AnnotatedPackage="com.Cv_Med.Hibernet";

	
	//we don't need this bean getDatasource
	//becuase session take hibernit.cfg.xml as ressource
	//and do evrything
	
	
	
	
	/*
	@Bean
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(Driver);
		dataSource.setUrl(Url);
		dataSource.setUsername(Username);
		dataSource.setPassword(Password);
		return dataSource;
	}*/

	/*@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(getDataSource());
		Properties properties = new Properties();
		
		//properties.put("hibernate.connection.pool_size", 1);
		//properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		//properties.put("hibernate.current_session_context_class", "thread");
		
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "update");
		factoryBean.setHibernateProperties(properties);
		//we use this for settign anotation for classes have @Entety
		//factoryBean.setAnnotatedClasses(User.class); !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//we cann check for package
		factoryBean.setAnnotatedPackages(AnnotatedPackage);
		
		return factoryBean;
	}*/
	
	/*@Bean
	@Scope("prototype")
	public LocalSessionFactoryBean sessionFactory() {
	    Resource resource = new ClassPathResource("hibernate.cfg.xml");
	    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	    sessionFactory.setConfigLocation(resource);
	    sessionFactory.setPackagesToScan("com.Cv_Med");
	    return sessionFactory ;
	}*/
	

	/*@Bean
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}*/

}