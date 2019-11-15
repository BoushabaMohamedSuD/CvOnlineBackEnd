package com.Cv_Med.Test_Jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!//
//you can use this in the controler if you don't need a hibernet.xml!!!!!!!!!!!!!!!!!!!!!!!!!
public class Test_Connectivity {
	String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false&serverTimezone=UTC";
	String user = "hbstudent";
	String pass = "hbstudent";
	 String Driver= "com.mysql.cj.jdbc.Driver";
	

	public void conection() {
		System.out.println("Connecting to database : " + jdbcUrl);
		 try {
			Class.forName( Driver );
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("error chargement driver");
			e1.printStackTrace();
		}
		try {
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
			System.out.println("Connection succesfly");
			
		} catch (SQLException e) {
			System.out.println("error connection driver");
			e.printStackTrace();
		}

		

	}

}
