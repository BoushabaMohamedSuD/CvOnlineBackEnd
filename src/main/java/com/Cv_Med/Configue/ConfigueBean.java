package com.Cv_Med.Configue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.Cv_Med.Bean.ErrorCatching;
import com.Cv_Med.Bean.GetContainer;
import com.Cv_Med.Bean.User_Operatin;
import com.Cv_Med.Hibernet.UserDaoImplimentation;
import com.Cv_Med.Service.ServiceDaoImplimentation;

@Configuration
public class ConfigueBean {
	// i remplace all those beans with defeult beans @compenet or @reposotry
	// @service
	// check the code

	/*
	 * @Bean
	 * 
	 * @Scope("prototype") public UserDaoImplimentation userDaoImplimentation() {
	 * return new UserDaoImplimentation(); }
	 * 
	 * @Bean
	 * 
	 * @Scope("prototype") public ServiceDaoImplimentation
	 * serviceDaoImplimentation() { return new ServiceDaoImplimentation(); }
	 */
	/*
	 * @Bean
	 * 
	 * @Scope("prototype") public User_Operatin user_operation() { return new
	 * User_Operatin(); }
	 * 
	 * 
	 * 
	 * 
	 * @Bean
	 * 
	 * @Scope("prototype") public GetContainer getContainer() { return new
	 * GetContainer(); }
	 */

	// done
	//we add those bean because 
	//it's been instantiate in classes 
	@Bean
	public ErrorCatching errorCatching() {
		return new ErrorCatching();
	}
	@Bean
	@Scope("prototype")
	public UserDaoImplimentation userDao() {
		return new UserDaoImplimentation();
	}

	

}
