package com.zj.study.framework.spring.cap11;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zj.study.framework.spring.cap11.config.Cap11MainConfig;
import com.zj.study.framework.spring.cap11.service.OrderService;

public class Cap11est {
	@Test
	public void test01() {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Cap11MainConfig.class);
		OrderService orderService = app.getBean(OrderService.class);
		orderService.addOrder();
		app.close();

	}
}
