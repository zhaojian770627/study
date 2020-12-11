package com.zj.study.framework.spring.lock.mdd;

import org.redisson.api.RedissonClient;

import com.zj.study.framework.spring.lock.mdd.redis.RedisClient;

public class LockClientHolder {

	private RedissonClient redissonClient;
	private RedisClient redisClient;
	
	public RedissonClient getRedissonClient() {
		return redissonClient;
	}

	public RedisClient getRedisClient() {
		return redisClient;
	}

	public void setRedissonClient(RedissonClient redissonClient) {
		this.redissonClient = redissonClient;
	}

	public void setRedisClient(RedisClient redisClient) {
		this.redisClient = redisClient;
	}

}
