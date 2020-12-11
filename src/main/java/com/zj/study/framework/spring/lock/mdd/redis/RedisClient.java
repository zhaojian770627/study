package com.zj.study.framework.spring.lock.mdd.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

public class RedisClient {
	private JedisPool pool = null;
	private final int dbindex;

	/**
	 * 传入ip和端口号构建redis 连接
	 * 
	 * @param ip   ip
	 * @param prot 端口
	 */
	public RedisClient(JedisPool pool, int dbindex) {
		this.pool = pool;
		this.dbindex = dbindex;
	}

	Jedis getJedis() {
		Jedis jedis = pool.getResource();
		jedis.select(dbindex);
		return jedis;
	}

	public String get(String key) {
		Jedis jedis = null;
		String value = null;

		try {
			jedis = getJedis();
			value = jedis.get(key);
		} finally {
			if (jedis != null)
				jedis.close();
		}
		return value;
	}

	public void expire(String key, int times) {
		Jedis jedis = null;

		try {
			jedis = getJedis();
			jedis.expire(key, times);
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}

	public boolean setnx(String key, String value, int duration) {
		Jedis jedis = null;
		try {
			String result;
			jedis = getJedis();
//			result = jedis.set(key, value, "nx", "ex", duration);
			result = jedis.set(key, value, SetParams.setParams().nx().ex(duration));
			return "OK".equalsIgnoreCase(result);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	public Long del(String... keys) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.del(keys);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
}
