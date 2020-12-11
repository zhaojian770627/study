package com.zj.study.framework.spring.lock.mdd;

import java.util.List;

import com.zj.study.framework.spring.lock.mdd.redis.RedisLockUtil;
import com.zj.study.framework.spring.lock.mdd.redisson.RedissonLockUtil;

public class LockFacade {

	public static final String lockPrex = "LOCK:FI:";

	public static String concat(String module, String lockey) {
		return LockFacade.lockPrex + module + ":" + lockey;
	}

	public static RedissonLockUtil useRedisson(String module) {
		return new RedissonLockUtil(module);
	}
	
	public static RedisLockUtil useRedisLockUtil(String module) {
		return new RedisLockUtil(module);
	}

	public static List<String> listLockKeys(String module) {
		return new RedissonLockUtil(module).listKeys();
	}

//	public static LockUtil useLockUtil(String module) {
//		return new LockUtil(module);
//	}
}
