package com.zj.study.framework.spring.cap10;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zj.study.framework.spring.cap10.aop.Calculator;
import com.zj.study.framework.spring.cap10.config.Cap10MainConfig;

public class Cap10est {
	@Test
	public void test01() {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Cap10MainConfig.class);

		Calculator calculator = app.getBean(Calculator.class);
		calculator.div(3, 1);
		app.close();

	}
}
