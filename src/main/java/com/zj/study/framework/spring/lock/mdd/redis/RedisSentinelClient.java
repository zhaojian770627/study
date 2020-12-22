package com.zj.study.framework.spring.lock.mdd.redis;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.zj.study.framework.spring.lock.config.RedisConf;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

public class RedisSentinelClient extends AbstractRedisClient {

	private String clientKey;
	private JedisSentinelPool jedisSentinelPool;
	private int dbindex;

	public RedisSentinelClient(RedisConf conf) {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		this.dbindex = conf.getMainIndex();
		poolConfig.setMaxTotal(conf.getMaxTotal());
		poolConfig.setMaxIdle(conf.getMaxIdle());
//		poolConfig.setMinIdle(conf.getMinIdle());
		String managerType = "default";// config.getManagerType();
//		if (StringUtils.isEmpty(managerType)) {
//			managerType = "default";
//		}

		this.clientKey = managerType;
//		if (config.getTest()) {
//			poolConfig.setTestOnBorrow(true);
//			poolConfig.setTestOnReturn(true);
//			poolConfig.setTestOnCreate(true);
//			poolConfig.setTestWhileIdle(true);
//		}

		String servers = conf.getServer();
		String[] serversArr = servers.split(",");
		Set<String> sentanels = new HashSet(Arrays.asList(serversArr));
//		poolConfig.setMaxWaitMillis((long) conf.getTimeout());
		poolConfig.setBlockWhenExhausted(true);
		this.jedisSentinelPool = new JedisSentinelPool(conf.getMasterName(), sentanels, poolConfig, conf.getTimeout(),
				conf.getRedisPassword());
	}

	@Override
	protected Object getJedis() {
		Jedis jedis = jedisSentinelPool.getResource();
		jedis.select(dbindex);
		return jedis;
	}
}
