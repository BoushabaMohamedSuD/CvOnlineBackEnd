package com.Cv_Med.Test_Bean;

public class Test_Bean2 {
	Dependency1 db1;
	private int i=0;
	public Test_Bean2( Dependency1 db1) {
		this.db1=db1;
		
	}

	public void lets_play() {
		System.out.println("---------------------------------------");
		if(db1!=null) {
			i++;
			System.out.println("dependency "+i);
		}else {
			System.out.println("nullll");
		}
		System.out.print("i am test 2 for a bean with no xml very nice work");
		db1.go();
	}
}
