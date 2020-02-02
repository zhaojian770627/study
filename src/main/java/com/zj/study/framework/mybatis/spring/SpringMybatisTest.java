package com.zj.study.framework.mybatis.spring;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zj.study.framework.mybatis.entity.TPosition;
import com.zj.study.framework.mybatis.entity.TUser;
import com.zj.study.framework.mybatis.spring.config.MainConfig;
import com.zj.study.framework.mybatis.spring.service.UserService;

public class SpringMybatisTest {
	@Test
	public void test01() {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		UserService userService = app.getBean(UserService.class);
		System.out.println(userService.getUserById(1));
		
		TUser user1 = new TUser();
		user1.setUserName("zj");
		user1.setRealName("zj");
		user1.setEmail("zj@163.com");
		TPosition positon = new TPosition();
		positon.setId(1);
		user1.setPosition(positon);
		
		userService.addUser(user1);
		
		app.close();

	}
}
