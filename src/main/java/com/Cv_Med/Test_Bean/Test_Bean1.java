package com.Cv_Med.Test_Bean;




public class Test_Bean1 {
	private int i=0;
	Dependency1 db1 ;

	
	public void lets_play() {
		System.out.println("---------------------------------------");
		if(db1!=null) {
			db1=new Dependency1();
			db1.go();
		}else {
			System.out.println("no dependency her");
		}
		
		System.out.println("i am test 1 for a bean with no xml very nice work");
		i++;
		System.out.println(i);
		
	}
	public void setDependency1(Dependency1 db1) {
		this.db1=db1;
		
	}
}
