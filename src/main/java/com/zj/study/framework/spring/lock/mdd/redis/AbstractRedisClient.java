package com.zj.study.framework.spring.lock.mdd.redis;

import redis.clients.jedis.Jedis;

public abstract class AbstractRedisClient implements IRedisClient {
	
	protected abstract Object getJedis();
	
	@Override
	public String get(String key) {
		Jedis jedis = null;
		String value = null;

		try {
			jedis = (Jedis) getJedis();
			value = jedis.get(key);
		} finally {
			if (jedis != null)
				jedis.close();
		}
		return value;
	}

	

	@Override
	public void expire(String key, int times) {
		Jedis jedis = null;

		try {
			jedis = (Jedis) getJedis();
			jedis.expire(key, times);
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}

	@Override
	public boolean setnx(String key, String value, int duration) {
		Jedis jedis = null;
		try {
			String result;
			jedis = (Jedis) getJedis();
			result = jedis.set(key, value, "nx", "ex", duration);
//			result = jedis.set(key, value, SetParams.setParams().nx().ex(duration));
			return "OK".equalsIgnoreCase(result);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	@Override
	public Long del(String... keys) {
		Jedis jedis = null;
		try {
			jedis = (Jedis) getJedis();
			return jedis.del(keys);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
}
