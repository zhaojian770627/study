package com.zj.study.framework.spring.lock.mdd;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zj.study.framework.spring.lock.config.LockBuilder;
import com.zj.study.framework.spring.lock.config.RedisConf;
import com.zj.study.framework.spring.lock.mdd.redis.RedisLockUtil;
import com.zj.study.framework.spring.lock.mdd.redisson.RedissonLockUtil;

@Service
public class LockFacade {
	@Value("${redis.server}")
	private String server;

	@Value("${redis.port}")
	private String port;

	@Value("${redis.password}")
	private String redisPassword;

	@Value("${redis.mainIndex}")
	private int mainIndex;

	LockClientHolder holder = new LockClientHolder();

	public LockFacade() {
		contructSelf();
	};

	private LockFacade contructSelf() {
		RedisConf conf = RedisConf.setConf().setServer(server).setPort(port).setRedisPassword(redisPassword)
				.setMainIndex(mainIndex);
		holder.setRedissonClient(LockBuilder.BuildRedissonClient(conf));
		holder.setRedisClient(LockBuilder.BuildRedisClient(conf));
		return null;
	}

	public static final String lockPrex = "LOCK:FI:";

	public static String concat(String module, String lockey) {
		return LockFacade.lockPrex + module + ":" + lockey;
	}

	public RedissonLockUtil useRedisson(String module) {
		return new RedissonLockUtil(module, holder.getRedissonClient());
	}

	public RedisLockUtil useRedisLockUtil(String module) {
		return new RedisLockUtil(module, holder.getRedisClient());
	}

	public List<String> listLockKeys(String module) {
		return new RedissonLockUtil(module, holder.getRedissonClient()).listKeys();
	}

//	public static LockUtil useLockUtil(String module) {
//		return new LockUtil(module);
//	}
}
