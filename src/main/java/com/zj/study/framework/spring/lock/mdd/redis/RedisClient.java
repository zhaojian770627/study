package com.zj.study.framework.spring.lock.mdd.redis;

import com.zj.study.framework.spring.lock.config.RedisConf;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisClient extends AbstractRedisClient {
	private JedisPool pool = null;
	private final int dbindex;

	/**
	 * 传入ip和端口号构建redis 连接
	 * 
	 * @param ip   ip
	 * @param prot 端口
	 */
	public RedisClient(RedisConf conf) {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(conf.getMaxTotal());
		config.setMaxIdle(conf.getMaxIdle());
		config.setMaxWaitMillis(conf.getMaxWaitMillis());
		config.setTestOnBorrow(true);
		pool = new JedisPool(config, conf.getServer(), Integer.valueOf(conf.getPort()), 100000,
				conf.getRedisPassword());
		this.dbindex = conf.getMainIndex();
	}

	protected Jedis getJedis() {
		Jedis jedis = pool.getResource();
		jedis.select(dbindex);
		return jedis;
	}
}
