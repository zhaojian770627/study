package com.zj.study.framework.redis.utils;

import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class JedisUtils {
	private JedisPool pool = null;
	private String ip = "192.168.3.11";
	private int port = 6379;
//	private String auth = "12345678";

	/**
	 * 传入ip和端口号构建redis 连接
	 * 
	 * @param ip   ip
	 * @param prot 端口
	 */
	public JedisUtils() {
		if (pool == null) {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(500);
			config.setMaxIdle(5);
			config.setMaxWaitMillis(100);
			config.setTestOnBorrow(true);
			pool = new JedisPool(config, this.ip, this.port, 100000, "123456a");
		}
	}

	public String get(String key) {
		Jedis jedis = null;
		String value = null;

		try {
			jedis = pool.getResource();
			value = jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null)
				jedis.close();
		}
		return value;
	}

	public Long incr(String key) {
		Jedis jedis = null;
		Long res = null;
		try {
			jedis = pool.getResource();
			res = jedis.incr(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null)
				jedis.close();
		}
		return res;
	}

	public Long sadd(String key, String... members) {
		Jedis jedis = null;
		Long res = null;
		try {
			jedis = pool.getResource();
			res = jedis.sadd(key, members);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null)
				jedis.close();
		}
		return res;
	}

	public void expire(String key, int times) {
		Jedis jedis = null;

		try {
			jedis = pool.getResource();
			jedis.expire(key, times);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null)
				jedis.close();
		}
	}

	public String hmset(String key, Map<String, String> hash) {
		Jedis jedis = null;
		String res = null;
		try {
			jedis = pool.getResource();
			res = jedis.hmset(key, hash);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null)
				jedis.close();
		}
		return res;
	}

	public Long zadd(String key, Map<String, Double> scoreMembers) {
		Jedis jedis = null;
		Long res = null;
		try {
			jedis = pool.getResource();
			res = jedis.zadd(key, scoreMembers);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null)
				jedis.close();
		}
		return res;
	}

	public Long zadd(String key, double score, String member) {
		Jedis jedis = null;
		Long res = null;
		try {
			jedis = pool.getResource();
			res = jedis.zadd(key, score, member);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null)
				jedis.close();
		}
		return res;
	}

	public Double zscore(String key, String member) {
		Jedis jedis = null;
		Double res = null;
		try {
			jedis = pool.getResource();
			res = jedis.zscore(key, member);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null)
				jedis.close();
		}
		return res;
	}

	public Double zincrby(String key, double score, String member) {
		Jedis jedis = null;
		Double res = null;
		try {
			jedis = pool.getResource();
			res = jedis.zincrby(key, score, member);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null)
				jedis.close();
		}
		return res;
	}

	public long hincrBy(String key, String field, long value) {
		Jedis jedis = null;
		long res = 0l;
		try {
			jedis = pool.getResource();
			res = jedis.hincrBy(key, field, value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null)
				jedis.close();
		}
		return res;
	}

	public Set<String> zrevrange(String key, long start, long end) {
		Jedis jedis = null;
		Set<String> res = null;
		try {
			jedis = pool.getResource();
			res = jedis.zrevrange(key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null)
				jedis.close();
		}
		return res;
	}

	public Map<String, String> hgetAll(String key) {
		Jedis jedis = null;
		Map<String, String> res = null;
		try {
			jedis = pool.getResource();
			res = jedis.hgetAll(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null)
				jedis.close();
		}
		return res;
	}

	public String hget(String key, String field) {
		Jedis jedis = null;
		String res = null;
		try {
			jedis = pool.getResource();
			res = jedis.hget(key, field);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null)
				jedis.close();
		}
		return res;
	}
}
