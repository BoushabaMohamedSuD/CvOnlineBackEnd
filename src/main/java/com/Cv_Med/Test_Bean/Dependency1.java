package com.Cv_Med.Test_Bean;

public class Dependency1 {
	private int i=0;
	private int c=0;
	public Dependency1() {
		
	}
	public void go() {
		i++;
		System.out.println("i am dependncy 1 nbr :" +i);
		System.out.println("------"+c+"---------");
	}
	public void change( int c) {
		this.c=c;
	}

}
