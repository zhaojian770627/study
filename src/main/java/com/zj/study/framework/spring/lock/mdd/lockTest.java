package com.zj.study.framework.spring.lock.mdd;

import java.util.concurrent.TimeUnit;

public class lockTest {

	public static void main(String[] args) throws InterruptedException {
		AppContext.init();
		LockFacade lockFacade = (LockFacade) AppContext.getBean("lockFacade");
		LockContext lockContext = lockFacade.useRedisLock("AA").lock("ZJAA", 1800, 2000);
		if (lockContext.isLocked()) {
			try {
				TimeUnit.SECONDS.sleep(10);
			} finally {
//				lockContext.unlock();
			}
		}
	}
}
