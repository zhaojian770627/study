package com.zj.study.framework.spring.lock.mdd;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

import com.zj.study.framework.spring.lock.config.LockBuilder;
import com.zj.study.framework.spring.lock.config.RedisConf;
import com.zj.study.framework.spring.lock.mdd.redis.RedisLockWrap;
import com.zj.study.framework.spring.lock.mdd.redisson.RedissonWrap;

public class LockFacade implements InitializingBean {
	@Value("${redis.address}")
	private String server;

	@Value("${redis.port}")
	private String port;

	@Value("${redis.password}")
	private String redisPassword;

	@Value("${redis.mainIndex}")
	private int mainIndex;

	LockClientHolder holder = new LockClientHolder();

	public LockFacade() {
//		contructSelf();
	};

	private void contructSelf() {
		RedisConf conf = RedisConf.setConf().setServer(server).setPort(port).setRedisPassword(redisPassword)
				.setMainIndex(mainIndex);
		holder.setRedissonClient(LockBuilder.BuildRedissonClient(conf));
		holder.setRedisClient(LockBuilder.BuildRedisClient(conf));
	}

	public static final String lockPrex = "LOCK:FI:";

	public static String concat(String module, String lockey) {
		return LockFacade.lockPrex + module + ":" + lockey;
	}

	public RedissonWrap useRedisson(String module) {
		return new RedissonWrap(module, holder.getRedissonClient());
	}

	public RedisLockWrap useRedisLock(String module) {
		return new RedisLockWrap(module, holder.getRedisClient());
	}

	public List<String> listLockKeys(String module) {
		return new RedissonWrap(module, holder.getRedissonClient()).listKeys();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		contructSelf();
	}

//	public static LockUtil useLockUtil(String module) {
//		return new LockUtil(module);
//	}
}
