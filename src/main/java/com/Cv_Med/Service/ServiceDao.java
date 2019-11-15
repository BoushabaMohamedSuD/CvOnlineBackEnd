package com.Cv_Med.Service;

import java.util.List;

import com.Cv_Med.Hibernet.User;
import com.Cv_Med.Test_Hibernet.Student;

public interface ServiceDao {
	public void add_user(User user);
	public void remove_user(int id);
	public User show_user(int id);
	public User show_user(String psuedo);
	public void update_user(int id ,String pseudo,String email,String password);
	public void update_user_connection(User user, Boolean connection);
	public void update_user_connection(int id, Boolean connection);
	
	public List<User> show_users_connected();
	public List<User> show_users();

}
