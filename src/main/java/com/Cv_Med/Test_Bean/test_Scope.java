package com.Cv_Med.Test_Bean;

import javax.annotation.PostConstruct;


import org.springframework.context.annotation.Scope;


public class test_Scope {
	
	private int a=0;
	private DependencyScope dbs;
	public test_Scope() {
		
	}
	public test_Scope(DependencyScope dbs) {
		this.dbs=dbs;
		
	}
	
	public DependencyScope getdbs() {
		return dbs;
	}
	
	public void letsplay() {
		a++;
		System.out.println(a);
	}
	public void letsgo() {
		dbs.go();
	}
	
	
	//for defining the first methode after the constroctor
	@PostConstruct
	private void init() {
		System.out.println("hhh i'am the first!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}
}
