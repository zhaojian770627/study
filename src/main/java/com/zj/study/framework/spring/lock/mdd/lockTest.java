package com.zj.study.framework.spring.lock.mdd;

import java.util.concurrent.TimeUnit;

import com.zj.study.framework.spring.lock.mdd.redisson.LockContext;

public class lockTest {

	public static void main(String[] args) throws InterruptedException {
		AppContext.init();

		LockContext lockContext = LockFacade.useRedisson("IA").lock("aaaa");
		System.err.println("lock success!");

		TimeUnit.SECONDS.sleep(100);

		lockContext.unlock();
		System.err.println("lock success!");
	}

}
