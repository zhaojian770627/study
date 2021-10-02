package com.zj.study.derby.lock;

import java.util.Hashtable;

import org.apache.derby.iapi.services.locks.Latch;
import org.apache.derby.iapi.services.locks.Lockable;

public class MyLockObject implements Lockable {

	@Override
	public void lockEvent(Latch lockInfo) {
		System.out.println(lockInfo);
	}

	@Override
	public boolean requestCompatible(Object requestedQualifier, Object grantedQualifier) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean lockerAlwaysCompatible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void unlockEvent(Latch lockInfo) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean lockAttributes(int flag, Hashtable<String, Object> attributes) {
		// TODO Auto-generated method stub
		return false;
	}

}
