package com.Cv_Med.Test_Bean;

public class Test_Bean3 {
	private Dependency2 db2_1;
	private Dependency2 db2_2;

	public Test_Bean3() {
		
	}
	public Test_Bean3(Dependency2 db1, Dependency2 db2) {
		this.db2_1=db1;
		this.db2_2=db2;
		
	}
	public void letsplay() {
		System.out.println("------------------");
		if(db2_1!=null&& db2_2!=null) {
			db2_1.go();
			db2_2.go();
		}else {
			System.out.println("noooooooooo");
		}
		
	}
	public void  setdep(Dependency2 db1,Dependency2 db2) {
		this.db2_1=db1;
		this.db2_2=db2;
		
	}
	
}
