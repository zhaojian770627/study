package com.zj.study.framework.spring.lock.redisson;

import org.redisson.Redisson;
import org.redisson.api.RRemoteService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zj.study.framework.spring.lock.config.MainConfig;

public class RedissonExecutorServerMain {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		Redisson redissonClient = (Redisson) app.getBean("redissonClient");

		RRemoteService remoteService = redissonClient.getRemoteService();
		ExecutorInterfaceImpl impl = new ExecutorInterfaceImpl();

		remoteService.register(ExecutorInterface.class, impl);
		System.out.println("server started!");
	}

}
