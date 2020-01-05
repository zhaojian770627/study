package com.zj.study.framework.spring.cap12;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zj.study.framework.spring.cap12.config.Cap12MainConfig;

public class Cap12Test {
	@Test
	public void test01() {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Cap12MainConfig.class);
		app.close();

	}
}
