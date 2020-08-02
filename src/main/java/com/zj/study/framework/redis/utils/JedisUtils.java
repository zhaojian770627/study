package com.zj.study.framework.redis.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

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
			pool = new JedisPool(config, this.ip, this.port, 100000,"123456a");
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
}
