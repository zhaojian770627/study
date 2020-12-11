package com.zj.study.framework.spring.lock.mdd;

import org.redisson.api.RLock;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.yonyoucloud.fi.basecom.util.lock.redis.LockUtil;

public class LockContext {
	String key;
	String[] keys;
	boolean success;
	RLock lock;

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

	private void unLockUseLockUtil() {
		if (this.keys != null) {
			RedisLockUtil.unLock(module, keys, ownerFlag);
		} else
			RedisLockUtil.unLock(module, key, ownerFlag);
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

	// 解锁
	public void unlock() {
		if (!success)
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
