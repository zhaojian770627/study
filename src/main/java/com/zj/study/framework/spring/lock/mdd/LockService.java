package com.zj.study.framework.spring.lock.mdd;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zj.study.framework.spring.lock.config.LockBuilder;
import com.zj.study.framework.spring.lock.config.RedisConf;
import com.zj.study.framework.spring.lock.mdd.redis.RedisLockWrap;
import com.zj.study.framework.spring.lock.mdd.redisson.RedissonWrap;

@Service
public class LockService implements InitializingBean {

	@Autowired
	RedisConf conf;

	LockClientHolder holder = new LockClientHolder();

	public LockService() {
//		contructSelf();
	};

	private void contructSelf() {
//		RedisConf conf = RedisConf.setConf().setServer(server).setPort(port).setRedisPassword(redisPassword)
//				.setMainIndex(mainIndex);
		holder.setRedissonClient(LockBuilder.BuildRedissonClient(conf));
		holder.setRedisClient(LockBuilder.BuildRedisClient(conf));
	}

	public static final String lockPrex = "LOCK:FI:";

	public static String concat(String module, String lockey) {
		return LockService.lockPrex + module + ":" + lockey;
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

	public boolean exist(String module, String key) {
		return new RedissonWrap(module, holder.getRedissonClient()).isExistsLock(key);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		contructSelf();
	}

//	public static LockUtil useLockUtil(String module) {
//		return new LockUtil(module);
//	}
}
