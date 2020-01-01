package com.zj.study.framework.spring.cap9.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zj.study.framework.spring.cap9.dao.TestDao;

@Configuration
@ComponentScan({ "com.zj.study.framework.spring.cap9.controller", "com.zj.study.framework.spring.cap9.service",
		"com.zj.study.framework.spring.cap9.bean"
		// ,"com.zj.study.framework.spring.cap9.dao"
})
public class Cap9MainConfig {
	// spring进行自装配的时候默认首选的bean
	// @Primary
	@Bean("testDao1")
	public TestDao testDao1() {
		TestDao testDao = new TestDao();
		testDao.setFlag("1");
		return testDao;
	}

	@Bean("testDao2")
	public TestDao testDao2() {
		TestDao testDao = new TestDao();
		testDao.setFlag("2");
		return testDao;
	}
}
