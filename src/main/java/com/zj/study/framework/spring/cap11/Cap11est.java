package com.zj.study.framework.spring.cap11;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zj.study.framework.spring.cap11.config.Cap11MainConfig;
import com.zj.study.framework.spring.cap11.dao.OrderDao;

public class Cap11est {
	@Test
	public void test01() {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Cap11MainConfig.class);
		OrderDao dao = app.getBean(OrderDao.class);
		dao.insert();
		app.close();

	}
}
