package com.zj.study.framework.spring.lock.mdd.redis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.StringUtils;

import com.zj.study.framework.spring.lock.mdd.AppContext;
import com.zj.study.framework.spring.lock.mdd.LockContext;
import com.zj.study.framework.spring.lock.mdd.LockFacade;
import com.zj.study.framework.spring.lock.mdd.UseType;

public class RedisLockUtil {
	final String module;

	private String ownerFlag;

	public RedisLockUtil(String module) {
		this.module = module;
	}

	private static RedisClient getClient() {
		return (RedisClient) AppContext.getBean("redisClient");
	}

//	private static String getLockV() {
//		String traceid = InvocationInfoProxy.getParameter("traceId");// AppContext.getThreadContext("traceid");
//		if (traceid == null) {
//			return AppContext.getTenantId() + "";
//		}
//		return traceid;
//	}

	/**
	 * traceid有问题与使用跟踪的策略有关
	 * 
	 * @param lockkey
	 * @return
	 */
	public LockContext lock(String lockkey, int delay) {
		boolean locked = innerLock(lockkey, delay);
		return newLockContext(lockkey, locked);
	}

	private boolean innerLock(String lockkey, int delay) {
		String traceid = ownerFlag == null ? "" : ownerFlag;
		String ident = LockFacade.concat(module, lockkey);
		boolean res = getClient().setnx(ident, traceid, delay);
		boolean locked;
		if (!res) {
			if (!StringUtils.isEmpty(traceid) && getClient().get(ident).equals(traceid)) {
				locked = true;
			} else
				locked = false;
		} else {
			locked = true;
		}
		return locked;
	}

	public static void unLock(String module, String lockkey, String ownerFlag) {
		String ident = LockFacade.concat(module, lockkey);
		String key = getClient().get(ident);
		if (!StringUtils.isEmpty(ownerFlag)) {
			if (ownerFlag.equals(key)) {
				getClient().del(ident);
			}
		} else {
			getClient().del(ident);
		}
	}

	public static void unLock(String module, String[] lockkeys, String ownerFlag) {
		for (String lockkey : lockkeys) {
			unLock(module, lockkey, ownerFlag);
		}
	}

	public static void expire(String module, String lockkey, String ownerFlag, int delay) {
		String ident = LockFacade.concat(module, lockkey);
		String key = getClient().get(ident);
		if (key == null)
			throw new RuntimeException("lock key not found,perhaps expired!");
		if (!StringUtils.isEmpty(ownerFlag)) {
			if (ownerFlag.equals(key)) {
				getClient().expire(ident, delay);
			}
		} else {
			getClient().expire(ident, delay);
		}
	}

	public LockContext lock(String lockkey, int delay, long ms_time) {
		int i = 1;
		do {
			LockContext lockContext = lock(lockkey, delay);
			if (lockContext.isLocked()) {
				return lockContext;
			} else {
				if (ms_time > 500 * i) {
					try {
						i++;
						Thread.sleep(500);
					} catch (InterruptedException e1) {
					}
				} else {
					return lockContext;
				}
			}
		} while (true);

	}

	/**
	 * 批量加锁
	 * 
	 * @param <T>
	 * @param lockPrex
	 * @param lockList
	 * @param requestId
	 * @return
	 * @return
	 * @throws RuntimeException
	 */
	public LockContext mlock(String[] lockkeys, int delay) throws RuntimeException {
		Arrays.sort(lockkeys);
		List<String> lockedList = new ArrayList<String>();
		boolean alllocked = true;
		String failedlockkey = null;
		try {
			for (String lockkey : lockkeys) {
				if (!innerLock(lockkey, delay)) {
					alllocked = false;
					failedlockkey = lockkey;
					break;
				}
				lockedList.add(lockkey);
			}

			if (!alllocked) {
				throw new RuntimeException(String.format("aquire lock key [%s] failed!", failedlockkey));
			}

			return newLockContext(lockkeys, alllocked);
		} finally {
			if (!alllocked) {
				for (String lock : lockedList) {
					unLock(module, lock, ownerFlag);
				}
			}
		}
	}

	private LockContext newLockContext(String lockKey, boolean locked) {
		LockContext lockContext = new LockContext();
		lockContext.setKey(lockKey);
		lockContext.setLocked(locked);
		lockContext.setUserType(UseType.LockUtil);
		lockContext.setOwnerFlag(ownerFlag);
		lockContext.setModule(module);
		return lockContext;
	}

	private LockContext newLockContext(String[] lockKeys, boolean locked) {
		LockContext lockContext = new LockContext();
		lockContext.setKeys(lockKeys);
		lockContext.setLocked(locked);
		lockContext.setUserType(UseType.LockUtil);
		lockContext.setOwnerFlag(ownerFlag);
		lockContext.setModule(module);
		return lockContext;
	}

	public RedisLockUtil setOwnerFlag(String request) {
		this.ownerFlag = request;
		return this;
	}

	public String getOwnerFlag() {
		return ownerFlag;
	}
}
