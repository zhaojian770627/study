package com.zj.study.derby.lock;

import org.apache.derby.iapi.error.StandardException;
import org.apache.derby.iapi.services.locks.C_LockFactory;
import org.apache.derby.iapi.services.locks.CompatibilitySpace;
import org.apache.derby.impl.services.locks.ConcurrentPool;

public class lockTest {

	public static void main(String[] args) throws StandardException {
		ConcurrentPool concurrentPool = new ConcurrentPool();
		MyLockOwner myLockOwner = new MyLockOwner(concurrentPool);
		myLockOwner.setNestedOwner(true);
		myLockOwner.setNestsUnder(true);
		myLockOwner.setNoWait(false);

		ILockGroup group = new LockGroup("g1");
		MyLockObject myLockObject = new MyLockObject();
		Object qualifier = new Object();

		CompatibilitySpace compatibilitySpace = myLockOwner.getCompatibilitySpace();

		boolean b = concurrentPool.lockObject(compatibilitySpace, group, myLockObject, qualifier,
				C_LockFactory.TIMED_WAIT);
		concurrentPool.unlock(compatibilitySpace, group, myLockObject, qualifier);
		System.out.println(b == true ? "success" : false);
		b = concurrentPool.lockObject(compatibilitySpace, group, myLockObject, qualifier, C_LockFactory.TIMED_WAIT);
		System.out.println(b == true ? "success" : false);
	}

}
