package com.zj.study.derby.lock;

import org.apache.derby.iapi.services.locks.CompatibilitySpace;
import org.apache.derby.iapi.services.locks.LockOwner;

public class MyLockSpace implements CompatibilitySpace {

	private LockOwner owner;

	@Override
	public LockOwner getOwner() {
		return this.owner;
	}

	public void setOwner(LockOwner owner) {
		this.owner = owner;
	}
}
