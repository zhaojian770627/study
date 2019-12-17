package com.zj.study.framework.lock.customlock;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ObjectLock implements Lock {
	private final Sync sycn;

	public ObjectLock(String lockKey) {
		super();
		sycn = new Sync(lockKey);
	}

	private static ConcurrentHashMap<String, Object> lockMap = new ConcurrentHashMap<>();

	private static class Sync extends AbstractQueuedLongSynchronizer {
		private final String lockKey;

		public Sync(String lockKey) {
			this.lockKey = lockKey;
		}

		@Override
		protected boolean tryAcquire(long arg) {
			if (lockMap.putIfAbsent(lockKey, lockKey) == null) {
				setExclusiveOwnerThread(Thread.currentThread());
				return true;
			}
			return false;
		}

		@Override
		protected boolean tryRelease(long arg) {
			if (lockMap.get(lockKey) == null)
				throw new UnsupportedOperationException();

			setExclusiveOwnerThread(null);
			setState(0);
			lockMap.remove(lockKey);
			return true;
		}

		@Override
		protected boolean isHeldExclusively() {
			return lockMap.contains(lockKey);
		}

		Condition newCondition() {
			return new ConditionObject();
		}

	}

	@Override
	public void lock() {
		sycn.acquire(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		sycn.acquireInterruptibly(1);
	}

	@Override
	public boolean tryLock() {
		return sycn.tryAcquire(1);
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return sycn.tryAcquireNanos(1, unit.toNanos(time));
	}

	@Override
	public void unlock() {
		sycn.release(1);
	}

	@Override
	public Condition newCondition() {
		return sycn.newCondition();
	}

}
