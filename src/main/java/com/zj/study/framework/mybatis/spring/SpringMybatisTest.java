package com.zj.study.framework.mybatis.spring;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zj.study.framework.mybatis.spring.config.MainConfig;

public class SpringMybatisTest {
	@Test
	public void test01() {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		// ConfigurableEnvironment environment = app.getEnvironment();
		// System.out.println("environment====" +
		// environment.getProperty("jdbc_driver"));
		// app.close();
		//
		// String[] names = app.getBeanDefinitionNames();
		//
		// for (String name : names) {
		// System.out.println(name);
		// }
		// app.close();
		
	}
}
