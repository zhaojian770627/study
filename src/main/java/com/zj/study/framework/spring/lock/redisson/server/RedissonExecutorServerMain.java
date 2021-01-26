package com.zj.study.framework.spring.lock.redisson.server;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zj.study.framework.spring.lock.config.MainConfig;
import com.zj.study.framework.spring.lock.config.VerInfo;

public class RedissonExecutorServerMain {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		VerInfo verInfo = app.getBean(VerInfo.class);
		System.out.println("server " + verInfo.getServerFlag() + " started!");
	}

}
