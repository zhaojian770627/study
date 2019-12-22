package com.zj.study.framework.spring.cap1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest1 {
	public static void main(String args[]) {
		ApplicationContext app = new ClassPathXmlApplicationContext("beans.xml");
		Person person = (Person) app.getBean("person");
		System.out.println(person);
	}
}
