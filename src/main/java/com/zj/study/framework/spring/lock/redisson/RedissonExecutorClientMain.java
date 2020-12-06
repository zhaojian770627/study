package com.zj.study.framework.spring.lock.redisson;

import java.util.concurrent.ExecutionException;

import org.redisson.Redisson;
import org.redisson.api.RFuture;
import org.redisson.api.RRemoteService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zj.study.framework.spring.lock.config.MainConfig;

public class RedissonExecutorClientMain {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		Redisson redissonClient = (Redisson) app.getBean("redissonClient");

		RRemoteService remoteService = redissonClient.getRemoteService();
		ExecutorInterfaceAsyc service1 = remoteService.get(ExecutorInterfaceAsyc.class);
		ExecutorInterfaceAsyc service2 = remoteService.get(ExecutorInterfaceAsyc.class);
		for (int i = 0; i < 100; i++) {
			RFuture<Integer> future = service1.executor("aaa", 1111);
			Integer result = future.get();
			System.out.println(result);
		}
	}

}
