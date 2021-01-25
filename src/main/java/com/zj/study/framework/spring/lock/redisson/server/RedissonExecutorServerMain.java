package com.zj.study.framework.spring.lock.redisson.server;

import java.util.UUID;

import org.redisson.Redisson;
import org.redisson.api.RRemoteService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zj.study.framework.spring.lock.config.MainConfig;
import com.zj.study.framework.spring.lock.redisson.common.ExecutorInterface;

public class RedissonExecutorServerMain {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		Redisson redissonClient = (Redisson) app.getBean("redissonClient");
		String flag = UUID.randomUUID().toString();
		RRemoteService remoteService = redissonClient.getRemoteService();
		ExecutorInterfaceImpl impl = new ExecutorInterfaceImpl(flag, app);

		remoteService.register(ExecutorInterface.class, impl, 1);
		System.out.println("server [" + flag + "] started!");
	}

}
