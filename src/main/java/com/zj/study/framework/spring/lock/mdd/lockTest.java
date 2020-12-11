package com.zj.study.framework.spring.lock.mdd;

import java.util.concurrent.TimeUnit;

public class lockTest {

	public static void main(String[] args) throws InterruptedException {
		AppContext.init();
		LockContext context = LockFacade.useRedisson("CC").mlock(new String[] { "aaa", "bbb", "ccc" });
		TimeUnit.SECONDS.sleep(10);
		context.unlock();
	}

}
