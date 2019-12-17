package com.zj.study.framework.lock.customlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 这个是有问题的
 * 
 * @author zj
 *
 */
public class ObjectLock2 extends ReentrantLock {
	volatile int refcnt = 1;

	public void addref() {
		refcnt++;
	}

	public void decref() {
		refcnt--;
	}
}
