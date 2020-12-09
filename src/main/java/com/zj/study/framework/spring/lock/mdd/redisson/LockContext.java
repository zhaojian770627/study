package com.zj.study.framework.spring.lock.mdd.redisson;

import org.redisson.api.RLock;

public class LockContext {
	String key;
	String[] keys;
	boolean success;
	RLock lock;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public RLock getLock() {
		return lock;
	}

	public void setLock(RLock lock) {
		this.lock = lock;
	}

	public void unlock() {
		if (success)
			lock.unlock();
	}

	public void setKeys(String[] lockkeys) {
		this.keys = lockkeys;
	}
}
