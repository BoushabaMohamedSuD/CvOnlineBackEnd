package com.Cv_Med.Test_Bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class BeanAutowiredTest {
	@Autowired
	@Qualifier("dependencyAutowiredTest")
	private DependencyAuowiredTest dat;
	
	public BeanAutowiredTest() {
		
	}
	public void letsplay() {
		System.out.println("lets play");
		dat.letsgo();
	}
}
