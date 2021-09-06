package com.zj.study.derby.lock;

import org.apache.derby.iapi.services.locks.LockOwner;

public class MyLockOwner implements LockOwner {

	@Override
	public boolean noWait() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isNestedOwner() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean nestsUnder(LockOwner other) {
		// TODO Auto-generated method stub
		return false;
	}

}
