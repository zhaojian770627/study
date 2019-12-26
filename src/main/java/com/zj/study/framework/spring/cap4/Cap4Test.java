package com.zj.study.framework.spring.cap4;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zj.study.framework.spring.cap4.config.Cap4MainConfig;

public class Cap4Test {
	@Test
	public void test01() {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Cap4MainConfig.class);

		System.out.println("IOC启动完毕........");
		app.getBean("person");

	}
}
