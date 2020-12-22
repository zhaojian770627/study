package com.zj.study.framework.spring.lock.mdd.redis;

public interface IRedisClient {

	void expire(String key, int times);

	boolean setnx(String key, String value, int duration);

	Long del(String... keys);

	String get(String key);

}
