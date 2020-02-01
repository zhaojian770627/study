package com.zj.study.framework.mybatis.spring;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zj.study.framework.mybatis.spring.config.MainConfig;

public class SpringMybatisTest {
	@Test
	public void test01() {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		app.close();
	}
}
