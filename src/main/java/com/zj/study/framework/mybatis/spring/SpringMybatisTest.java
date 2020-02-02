package com.zj.study.framework.mybatis.spring;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zj.study.framework.mybatis.spring.config.MainConfig;
import com.zj.study.framework.mybatis.spring.service.UserService;

public class SpringMybatisTest {
	@Test
	public void test01() {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		UserService userService = app.getBean(UserService.class);
		System.out.println(userService.getUserById(1));
		app.close();

	}
}
