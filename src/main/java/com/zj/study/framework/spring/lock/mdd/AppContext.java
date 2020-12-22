package com.zj.study.framework.spring.lock.mdd;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zj.study.framework.spring.lock.config.MainConfig;

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
