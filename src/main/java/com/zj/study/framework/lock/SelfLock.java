package com.zj.study.framework.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class SelfLock implements Lock {

	private static class Sync extends AbstractQueuedLongSynchronizer {

		@Override
		protected boolean tryAcquire(long arg) {
			if (compareAndSetState(0, 1)) {
				setExclusiveOwnerThread(Thread.currentThread());
				return true;
			}
			return false;
		}

		@Override
		protected boolean tryRelease(long arg) {
			if (getState() == 0)
				throw new UnsupportedOperationException();

			setExclusiveOwnerThread(null);
			setState(0);
			return true;
		}

		@Override
		protected boolean isHeldExclusively() {
			return getState() == 1;
		}

		Condition newCondition() {
			return new ConditionObject();
		}

	}

	private final Sync sycn = new Sync();

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
