package com.zj.study.framework.spring.lock.mdd;

public class lockTest {

	public static void main(String[] args) throws InterruptedException {
		AppContext.init();
		LockService lockService = (LockService) AppContext.getBean(LockService.class);

		LockContext lockContext = lockService.useRedisson("FIML").trylock("B0");
		String msg = "";
		if (lockContext.isLocked()) {
			msg = "加锁成功";
			lockContext.unlock();
		} else
			msg = "加锁失败";
		System.out.println(msg);
	}
}
