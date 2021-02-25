package com.zj.study.work.tools.checkbalance;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class AppContext {
	static AnnotationConfigApplicationContext app;

	public static Object getBean(Class<?> clz) {
		return app.getBean(clz);
	}

	public static Object getBean(String beanName) {
		return app.getBean(beanName);
	}

	public static void init() {
		app = new AnnotationConfigApplicationContext(MainConfig.class);
	}
}
