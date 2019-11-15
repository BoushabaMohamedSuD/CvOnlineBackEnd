package com.Cv_Med.Bean;

import com.Cv_Med.Hibernet.User;

public interface Operation {
	public User login(String pseudo,String email,String password);
	public User registre(String pseudo,String email,String password,String confirm_password);
	public void logout(int id);

}
