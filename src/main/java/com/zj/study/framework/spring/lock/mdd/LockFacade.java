package com.zj.study.framework.spring.lock.mdd;

import java.util.List;

import com.zj.study.framework.spring.lock.mdd.redisson.RedissonLockUtil;

public class LockFacade {

	public static final String lockPrex = "LOCK:FI:";

	public static RedissonLockUtil useRedisson(String module) {
		return new RedissonLockUtil(module);
	}

	public static List<String> listLockKeys(String module) {
		return new RedissonLockUtil(module).listKeys();
	}

}
