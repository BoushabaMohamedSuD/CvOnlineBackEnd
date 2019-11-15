package com.Cv_Med.Configue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.Cv_Med.Hibernet.UserDaoImplimentation;
import com.Cv_Med.JWT.CORSFilre;
import com.Cv_Med.JWT.JwtAuthenticationFilter;
import com.Cv_Med.JWT.JwtAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class Confique_Security extends WebSecurityConfigurerAdapter {

	// because the default name of my service
	// is serviceDao...........
	/*
	 * @Autowired private Configue_Securit_Service configue_Securit_Service;
	 */
	
	
	
	
	
	/*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!the default url for login in spring securty 
	 * to send post request is /login
	 * so use it for authentificate in rest api!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 */
	@Autowired
	private UserDaoImplimentation userDaoImplimentation;
	

	@Bean
	public UserDetailsService userDetailsService() {
		return new Configue_Securit_Service();

	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		// you have to set the type of the cryptage
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());

		/*
		 * UserBuilder users=User.withDefaultPasswordEncoder();
		 * auth.inMemoryAuthentication()
		 * .withUser(users.username("aze").password("aze").roles("ADMIN"))
		 * .withUser(users.username("azev").password("azev").roles("USER"));
		 */
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * http.csrf().disable().authorizeRequests().antMatchers("/loginsecurty").
		 * permitAll() .anyRequest().authenticated().and()
		 * .formLogin().loginPage("/loginsecurty").permitAll().and()
		 * .logout().invalidateHttpSession(true).clearAuthentication(true)
		 * .logoutRequestMatcher(new AntPathRequestMatcher("/logoutsecurity"))
		 * .logoutSuccessUrl("/logout_succes").permitAll();
		 */

		/*
		 * http.authorizeRequests().anyRequest().authenticated() .and().httpBasic();
		 */

		/*
		 * http.formLogin(); http.authorizeRequests().antMatchers("/").hasAnyRole();
		 * http.authorizeRequests().antMatchers("/index").hasAnyRole();
		 * http.authorizeRequests().antMatchers("/login1").hasRole("Admin");
		 * http.authorizeRequests().antMatchers("/profile").hasRole("Admin");
		 */

		// this configeu for
		// jsp!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		/*
		 *YOU WILL NO ABLE TO CONNECT CORRECTLY AND SAVE SESSION IN FORM LOGIN
		 * FOR DOING THAT YOU HAVE TO ACTIVATE SESSION
		 * BY DELETING .SESSIONmANAGEMENT() and .sessionCreationPolicy()
		 * HOPE THAT you will understand what i am saying in the futur if you forget
		 */

		http.csrf().disable().cors().and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				// add jwt filters (1. authentication, 2. authorization)
				.addFilter(new JwtAuthenticationFilter(authenticationManager()))
				.addFilter(new JwtAuthorizationFilter(authenticationManager(), this.userDaoImplimentation))
				.addFilterBefore(new CORSFilre(), ChannelProcessingFilter.class)
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/login").permitAll()
				.antMatchers(HttpMethod.POST, "/registre").permitAll()
				.antMatchers(HttpMethod.POST, "/ListTest").permitAll()
				.antMatchers(HttpMethod.POST, "/addcv").permitAll()
				.antMatchers(HttpMethod.POST, "/begunUpload").authenticated()
				.antMatchers(HttpMethod.POST, "/uploadMultiFile").authenticated()
				.antMatchers(HttpMethod.GET, "/getimagetest").permitAll()
				.antMatchers(HttpMethod.POST,"/isConnectedjwt").authenticated()
				.antMatchers(HttpMethod.POST,"/testjwtauth").authenticated()
				.antMatchers(HttpMethod.POST,"/UplaodImage").authenticated()
				.antMatchers(HttpMethod.POST,"/CreateNewCv").authenticated()
				.antMatchers(HttpMethod.POST,"/UpdateCv").authenticated()
				.antMatchers(HttpMethod.GET,"/ShowCv").authenticated()
				.antMatchers(HttpMethod.POST,"/CreateNewFormation").authenticated()
				.antMatchers(HttpMethod.POST,"/CreateNewInfoPersonal").authenticated()
				.antMatchers(HttpMethod.POST,"/CreateNewCompetence").authenticated()
				.antMatchers(HttpMethod.POST,"/CreateNewLanguage").authenticated()
				.antMatchers(HttpMethod.POST,"/CreateNewLeisure").authenticated()
				.antMatchers(HttpMethod.GET,"/Getimage").authenticated() 
				.antMatchers("/cv").hasAnyRole("User", "Admin", "Boss").antMatchers("/cv")
				.hasAnyRole("USER", "Admin", "Boss").antMatchers("/cv_exemple").hasAnyRole("USER", "Admin", "Boss")
				.antMatchers("/detail_project").hasAnyRole("USER", "Admin", "Boss").antMatchers("/export")
				.hasAnyRole("USER", "Admin", "Boss")
				// .antMatchers("/logout").hasAnyRole("User","Admin","Boss")
				.antMatchers("/profile").hasAnyRole("USER", "Admin", "Boss").antMatchers("/style1")
				.hasAnyRole("USER", "Admin", "Boss").and().formLogin().loginPage("/loginFormJsp")
				.loginProcessingUrl("/loginProcess").defaultSuccessUrl("/").permitAll().and().logout().permitAll();

		// end!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		// this confique for angular and
		// jwt!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		/*
		 * http // remove csrf and state in session because in jwt we do not need them
		 * .csrf().disable()
		 * .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		 * .and() // add jwt filters (1. authentication, 2. authorization)
		 * .addFilter(new JwtAuthenticationFilter(authenticationManager()))
		 * .addFilter(new JwtAuthorizationFilter(authenticationManager(),
		 * this.userDaoImplimentation)) .authorizeRequests() // configure access rules
		 * .antMatchers(HttpMethod.POST, "/Login").permitAll()
		 * .anyRequest().authenticated();
		 */

		// end!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		/*
		 * .and() .addFilterAfter( new Confique_Securty_Filter(),
		 * BasicAuthenticationFilter.class);
		 */

	}
	
	
	 
	

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

}
