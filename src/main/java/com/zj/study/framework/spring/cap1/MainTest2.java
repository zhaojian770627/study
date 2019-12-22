package com.zj.study.framework.spring.cap1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.enjoy.cap1.config.MainConfig;

public class MainTest2 { 
	public static void main(String args[]){
		
		ApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		
		// Person person = (Person) app.getBean("james");
		// System.out.println(person);
		
		String[] namesForBean = app.getBeanNamesForType(Person.class);
		for(String name:namesForBean){
			System.out.println(name);
		}
		
		
	}
}
