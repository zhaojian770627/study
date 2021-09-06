package com.zj.study.derby.lock;

import org.apache.derby.iapi.error.StandardException;
import org.apache.derby.iapi.services.locks.C_LockFactory;
import org.apache.derby.impl.services.locks.ConcurrentPool;

public class lockTest {

	public static void main(String[] args) throws StandardException {
		ConcurrentPool concurrentPool = new ConcurrentPool();
		MyLockOwner myLockOwner = new MyLockOwner(concurrentPool);
		myLockOwner.setNestedOwner(true);
		myLockOwner.setNestsUnder(true);
		myLockOwner.setNoWait(true);

		Object group = new Object();
		MyLockObject myLockObject = new MyLockObject();
		Object qualifier = new Object();

		concurrentPool.lockObject(myLockOwner.getCompatibilitySpace(), group, myLockObject, qualifier,
				C_LockFactory.TIMED_WAIT);
	}

}
