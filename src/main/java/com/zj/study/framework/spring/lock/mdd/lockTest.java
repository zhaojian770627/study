package com.zj.study.framework.spring.lock.mdd;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class lockTest {

	public static void main(String[] args) throws InterruptedException {
		AppContext.init();

		LockContext lockContext = LockFacade.useRedisson("IA").lock("aaaa");
		System.err.println("lock success!");

		TimeUnit.SECONDS.sleep(10);
		List<String> listLockKeys = LockFacade.listLockKeys("IA");

		for (String lockKey : listLockKeys)
			System.err.println(lockKey);

		lockContext.unlock();
		System.err.println("unlock success!");
	}

}
