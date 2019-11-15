package com.Cv_Med.Test_Hibernet;

import java.util.List;



import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import org.hibernate.Session;

import org.hibernate.SessionFactory;

@Repository
public class StudentimplementDao implements StudentDao {

	private SessionFactory sf;
	private int i=0;

	public StudentimplementDao() {
		sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
		

	}

	@Override
	public void add_Student(Student student) {
		
		boolean kle_save = true;
		List<Student> students = show_students();
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		for (Student st : students) {
			if ((student.getPseudo().equals(st.getPseudo())) || (student.getEmail().equals(st.getEmail()))) {
				kle_save = false;
				break;
			}
			System.out.println("ssssssssssssssssssssssssssssssssssssssssssssss");

		}
		if (kle_save) {
			s.save(student);
		} else {
			System.out.println("we can't save");
		}
		s.getTransaction().commit();

	}

	@Override
	public void remove_Student(int id) {
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		Student student = s.get(Student.class, id);
		s.delete(student);
		s.getTransaction().commit();

	}

	@Override
	public Student show_student(int id) {
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		Student student = s.get(Student.class, id);
		s.getTransaction().commit();
		return student;
	}

	@Override
	public void update_student(int id, String pseudo, String email, String password) {
		
		boolean kle_pseudo_update = true;
		boolean kle_email_update = true;
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		Student student = s.get(Student.class, id);

		List<Student> students = show_students();
		for (Student st : students) {
			if (pseudo.equals(st.getPseudo())) {

				if (kle_email_update) {
					kle_pseudo_update = false;
				} else {
					kle_pseudo_update = false;
					break;
				}

			}
			if (email.equals(st.getPassword())) {

				if (kle_pseudo_update) {
					kle_email_update = false;
				} else {
					kle_email_update = false;
					break;
				}

			}

		}
		if (kle_email_update) {
			student.setEmail(email);
		} else {
			System.out.println("email already existe");
		}
		if (kle_pseudo_update = false) {
			student.setPseudo(pseudo);
		} else {
			System.out.println("pseudo already existe");
		}

		student.setPassword(password);
		s.getTransaction().commit();

	}

	@Override
	public List<Student> show_students() {
		
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		List<Student> students = s.createQuery("from Student").getResultList();
		s.getTransaction().commit();
		return students;
	}
	
	
	
	public void letplay() {
		i++;
		System.out.println(i+" daonumber ");
	}

}
