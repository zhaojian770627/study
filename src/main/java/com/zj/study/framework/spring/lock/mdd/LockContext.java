package com.zj.study.framework.spring.lock.mdd;

import org.redisson.api.RLock;

public class LockContext {
	String key;
	String[] keys;
	boolean success;
	RLock lock;

	// 表明用了哪种实现
	UseType userType;

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

	public UseType getUserType() {
		return userType;
	}

	public void setUserType(UseType userType) {
		this.userType = userType;
	}
}
