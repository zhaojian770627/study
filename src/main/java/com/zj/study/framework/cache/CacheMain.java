package com.zj.study.framework.cache;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CacheMain {

	public static void main(String[] args) throws IOException {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		System.out.println(app.getBean(FacadeBean.class));
		System.in.read();
	}

}
