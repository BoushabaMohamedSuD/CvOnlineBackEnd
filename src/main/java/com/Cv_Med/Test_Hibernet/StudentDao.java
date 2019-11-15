package com.Cv_Med.Test_Hibernet;

import java.util.List;

public interface StudentDao {
	public void add_Student(Student student);
	public void remove_Student(int id);
	public Student show_student(int id);
	public void update_student(int id ,String pseudo,String email,String password);
	
	
	public List<Student> show_students();
		
	
	

}
