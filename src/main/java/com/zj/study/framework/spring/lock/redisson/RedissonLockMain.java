package com.zj.study.framework.spring.lock.redisson;

import java.util.concurrent.TimeUnit;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zj.study.framework.spring.lock.config.MainConfig;

public class RedissonLockMain {

	public static void main(String[] args) throws InterruptedException {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		Redisson redissonClient = (Redisson) app.getBean("redissonClient");

		RLock lock = redissonClient.getLock("zj1234");
		System.out.println("locked");
		lock.lock();
		System.out.println("sleep begin");
		TimeUnit.MINUTES.sleep(5);
		System.out.println("sleep end");
		lock.unlock();
		System.out.println("unlock");

	}

}
