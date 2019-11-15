package com.Cv_Med.Configue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Cv_Med.Hibernet.User;
import com.Cv_Med.Hibernet.UserDaoImplimentation;


@Service
public class Configue_Securit_Service  implements  UserDetailsService {
	@Autowired
	private UserDaoImplimentation userDaoImplimentation  ;
	
	


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userDaoImplimentation.show_user(username);
		if(user==null) {
			System.out.println("user null from service spring security");
			try {
				throw new Exception("user name wrong");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		if(user.getPassword()==null) {
			System.out.println("password null");
			try {
				throw new Exception("no passwsord in the user");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}else {
			return new UserPrincipal(user);
		}
		
		
	}

}
