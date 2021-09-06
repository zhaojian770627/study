package com.zj.study.derby.lock;

import org.apache.derby.iapi.error.StandardException;
import org.apache.derby.iapi.services.locks.C_LockFactory;
import org.apache.derby.impl.services.locks.ConcurrentPool;

public class lockTest {

	public static void main(String[] args) throws StandardException {
		ConcurrentPool concurrentPool = new ConcurrentPool();
		MyLockOwner myLockOwner = new MyLockOwner();
		myLockOwner.setNestedOwner(true);
		myLockOwner.setNestsUnder(true);
		myLockOwner.setNoWait(true);

		LockSpace lockSpace = new LockSpace();
		lockSpace.setOwner(myLockOwner);

		Object group = new Object();
		MyLockObject myLockObject = new MyLockObject();
		Object qualifier = new Object();

		concurrentPool.lockObject(lockSpace, group, myLockObject, qualifier, C_LockFactory.TIMED_WAIT);
	}

}
