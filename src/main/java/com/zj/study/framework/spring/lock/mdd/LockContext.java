package com.zj.study.framework.spring.lock.mdd;

import java.util.concurrent.locks.Lock;

import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.zj.study.framework.spring.lock.mdd.redis.RedisLockWrap;

public class LockContext {
	String key;
	String[] keys;
	private boolean locked;
	Lock lock;

	RedisLockWrap redisLockUtil;

	// 从加锁信息中带出来
	private String ownerFlag;
	private String module;

	public String getOwnerFlag() {
		return ownerFlag;
	}

	public void setOwnerFlag(String ownerFlag) {
		this.ownerFlag = ownerFlag;
	}

	// 表明用了哪种实现
	UseType useType;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public Lock getLock() {
		return lock;
	}

	public void setLock(Lock lock) {
		this.lock = lock;
	}

	private void unLockUseLockUtil() {
		if (this.keys != null) {
			redisLockUtil.unLock(module, keys, ownerFlag);
		} else
			redisLockUtil.unLock(module, key, ownerFlag);
	}

	private void unLockUseRedisson() {
		lock.unlock();
	}

	public void setKeys(String[] lockkeys) {
		this.keys = lockkeys;
	}

	public UseType getUseType() {
		return useType;
	}

	public void setUserType(UseType useType) {
		this.useType = useType;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}
	
	public RedisLockWrap getRedisLockUtil() {
		return redisLockUtil;
	}

	public void setRedisLockUtil(RedisLockWrap redisLockUtil) {
		this.redisLockUtil = redisLockUtil;
	}

	// 解锁
	public void unlock() {
		if (!locked)
			return;
		if (this.useType.equals(UseType.Redisson)) {
			unLockUseRedisson();
		} else {
			unLockUseLockUtil();
		}
	}

	/**
	 * 事务完成后放锁，不管事务成功和失败
	 * 
	 * 此方法必须放在Spring管理的事务中
	 * 
	 */
	public void unlockAfterTrans() {
		LockReleseAfterTran lockRelese = new LockReleseAfterTran(this);
		TransactionSynchronizationManager.registerSynchronization(lockRelese);
	}
}
