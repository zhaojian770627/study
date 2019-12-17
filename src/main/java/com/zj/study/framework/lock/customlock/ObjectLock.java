package com.zj.study.framework.lock.customlock;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 这个是有问题的
 * 
 * @author zj
 *
 */
public class ObjectLock implements Lock {
	private final String lockkey;

	public ObjectLock(String lockkey) {
		super();
		this.lockkey = lockkey;
	}

	private static ConcurrentHashMap<String, ObjectLock2> lockMap = new ConcurrentHashMap<>();

	@Override
	public void lock() {
		ObjectLock2 lock = lockMap.putIfAbsent(lockkey, new ObjectLock2());
		if (lock == null) {
			lock = lockMap.get(lockkey);
		} else
			lock.addref();

		lock.lock();
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean tryLock() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void unlock() {
		ObjectLock2 lock = lockMap.get(lockkey);
		lock.decref();
	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}

}
