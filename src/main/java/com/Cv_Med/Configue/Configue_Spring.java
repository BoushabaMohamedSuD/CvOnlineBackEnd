package com.Cv_Med.Configue;

import javax.servlet.MultipartConfigElement;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.Cv_Med.Test_Bean.Test_Bean1;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages= "com.Cv_Med")

//@ComponentScan(basePackages = { "com.Cv_Med" }, excludeFilters = { @Filter(type = FilterType.ANNOTATION, value = Configuration.class) })



public class Configue_Spring implements WebMvcConfigurer /*, WebApplicationInitializer*/{
	private String TMP_FOLDER = "/tmp";
	private int MAX_UPLOAD_SIZE = 5 * 1024 * 1024;

	
	@Bean
	public ViewResolver vienResolver() {
		InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
		viewResolver.setPrefix("/jsp_project/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
		
	}
	
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/jsp_project/**").addResourceLocations("/jsp_project/");
    }

	/*@Override
	public void onStartup(ServletContext sc) throws ServletException {

		ServletRegistration.Dynamic appServlet = sc.addServlet("mvc",
				(Servlet) new DispatcherServlet(new GenericWebApplicationContext()));

		appServlet.setLoadOnStartup(1);

		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(TMP_FOLDER, MAX_UPLOAD_SIZE,
				MAX_UPLOAD_SIZE * 2, MAX_UPLOAD_SIZE / 2);

		appServlet.setMultipartConfig(multipartConfigElement);
	}*/
	
	
	
	

}
