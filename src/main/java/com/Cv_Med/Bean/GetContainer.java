package com.Cv_Med.Bean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.Cv_Med.Configue.ConfigueBean;
import com.Cv_Med.Configue.Configue_BeanTest;

@Component
@Scope("prototype")
public class GetContainer {
	private AnnotationConfigApplicationContext context;
	private AnnotationConfigApplicationContext context_test;
	public GetContainer() {
		this.context = new AnnotationConfigApplicationContext(ConfigueBean.class);
		this.context_test=new AnnotationConfigApplicationContext(Configue_BeanTest.class);
	}
	public AnnotationConfigApplicationContext getContext() {
		return context;
	}
	public AnnotationConfigApplicationContext getContextTest() {
		return context_test;
	}

}
