package com.zj.study.framework.spring.zj01;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zj.study.framework.spring.zj01.config.ZJ01MainConfig;
import com.zj.study.framework.spring.zj01.service.OrderService;

public class zj01est {
	@Test
	public void test01() {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(ZJ01MainConfig.class);
		BeanDefinition define=app.getBeanDefinition("orderDao");
		String[] names = app.getBeanDefinitionNames();
		for (String name : names) {
			System.err.println(name);
		}
		OrderService orderService = app.getBean(OrderService.class);
		orderService.addOrder();
		app.close();

	}
}
