package com.zj.study.framework.spring.lock.mdd;

import org.redisson.api.RedissonClient;

import com.zj.study.framework.spring.lock.mdd.redis.IRedisClient;

public class LockClientHolder {

	private RedissonClient redissonClient;
	private IRedisClient redisClient;

	public RedissonClient getRedissonClient() {
		return redissonClient;
	}

	public IRedisClient getRedisClient() {
		return redisClient;
	}

	public void setRedissonClient(RedissonClient redissonClient) {
		this.redissonClient = redissonClient;
	}

	public void setRedisClient(IRedisClient redisClient) {
		this.redisClient = redisClient;
	}

}
