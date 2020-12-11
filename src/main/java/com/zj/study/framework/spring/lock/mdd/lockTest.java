package com.zj.study.framework.spring.lock.mdd;

import java.util.concurrent.TimeUnit;

public class lockTest {

	public static void main(String[] args) throws InterruptedException {
		AppContext.init();
		LockContext lockContext = LockFacade.useRedisLockUtil("aaa").lock("aaaa", 20);
		if (lockContext.isLocked()) {
			try {
				TimeUnit.SECONDS.sleep(10);
			} finally {
				lockContext.unlock();
			}
		}
	}
}
