package com.zj.study.framework.spring.cap3.config;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Cap3Test {
	@Test
	public void test01(){
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Cap3MainConfig.class);
		
		
		String[] names = app.getBeanDefinitionNames();
		
		for(String name:names){
			System.out.println(name);
		}
		//�������зֱ�ȡ����personʵ��, ���Ƿ�Ϊͬһ��bean
		Object bean1 = app.getBean("person");
		Object bean2 = app.getBean("person");
		System.out.println(bean1 == bean2);
		//����:bean1����bean2,ͬһ������
		
	}
}
