package com.Cv_Med.Test_Bean;

public class Dependency2 {

	private int a;
	private int b;
	private int change;
	
	
	public Dependency2() {
		System.out.println("constructure yessssssssss !!!!!!!!!!!!!!!!!!!!!!!!!!!! for dependency 2");
	}
	public void go() {
		
		if(a!=0 && b!=0) {
			System.out.println(a+" - "+b);
			if(change!=0) {
				System.out.println(change);
			}else {
				System.out.println("no change");
			}
		}else {
			System.out.println("it's seem that there are a mistake here");
		}
	}
	public void setchange(int c) {
		this.change=c;
	}
	public void setint(int a,int b) {
		this.a=a;
		this.b=b;
	}
}
