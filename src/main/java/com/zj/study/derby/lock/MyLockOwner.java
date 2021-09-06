package com.zj.study.derby.lock;

import org.apache.derby.iapi.services.locks.CompatibilitySpace;
import org.apache.derby.iapi.services.locks.LockOwner;
import org.apache.derby.impl.services.locks.ConcurrentPool;

public class MyLockOwner implements LockOwner {

	private boolean noWait;
	private boolean isNestedOwner;
	private boolean nestsUnder;
	private CompatibilitySpace compatibilitySpace;

	public MyLockOwner(ConcurrentPool concurrentPool) {
		this.compatibilitySpace = concurrentPool.createCompatibilitySpace(this);
	}

	@Override
	public boolean noWait() {
		return this.noWait;
	}

	@Override
	public boolean isNestedOwner() {
		return this.isNestedOwner;
	}

	@Override
	public boolean nestsUnder(LockOwner other) {
		return this.nestsUnder;
	}

	public boolean isNoWait() {
		return noWait;
	}

	public void setNoWait(boolean noWait) {
		this.noWait = noWait;
	}

	public boolean isNestsUnder() {
		return nestsUnder;
	}

	public void setNestsUnder(boolean nestsUnder) {
		this.nestsUnder = nestsUnder;
	}

	public void setNestedOwner(boolean isNestedOwner) {
		this.isNestedOwner = isNestedOwner;
	}

	public CompatibilitySpace getCompatibilitySpace() {
		return compatibilitySpace;
	}

	public void setCompatibilitySpace(CompatibilitySpace compatibilitySpace) {
		this.compatibilitySpace = compatibilitySpace;
	}

}
