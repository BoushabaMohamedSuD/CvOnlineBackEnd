package com.Cv_Med.Hibernet;

import java.util.List;



public interface UserDao {
	public void add_user(User user);
	public void remove_user(int id);
	public User show_user(int id);
	public User show_user(String pseudo);
	public void update_user(int id ,String pseudo,String email,String password);
	public void update_user_connection(User user, Boolean connection);
	public void update_user_connection(int id, Boolean connection);
	
	public List<User> show_users_connected();
	public List<User> show_users();

}
