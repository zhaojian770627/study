package com.zj.study.derby.lock;

import org.apache.derby.iapi.services.locks.LockOwner;

public class MyLockOwner implements LockOwner {

	private boolean noWait;
	private boolean isNestedOwner;
	private boolean nestsUnder;

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

}
