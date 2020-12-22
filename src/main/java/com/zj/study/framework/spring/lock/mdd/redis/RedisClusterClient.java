package com.zj.study.framework.spring.lock.mdd.redis;

import java.util.HashSet;
import java.util.Set;

import com.zj.study.framework.spring.lock.config.RedisConf;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.params.SetParams;

public class RedisClusterClient extends AbstractRedisClient {
	private final int dbindex;

	protected JedisCluster jedisCluster;
	private String clientKey;
	private int soTimeout = 3000;
	private int connectionTimeout = 5000;
	private int maxAttempts = 10;

	/**
	 * 传入ip和端口号构建redis 连接
	 * 
	 * @param ip   ip
	 * @param prot 端口
	 */
	public RedisClusterClient(RedisConf redisConf) {
		this.dbindex = redisConf.getMainIndex();
		Set<HostAndPort> nodes = new HashSet();
		String[] hostStrs = redisConf.getServer().split(",");
		String[] hosts = hostStrs;
		int hosutCount = hostStrs.length;

		for (int pos = 0; pos < hosutCount; ++pos) {
			String hostStr = hosts[pos];
			String[] st = hostStr.split(":");
			if (st.length != 2) {
				throw new IllegalArgumentException("Illegal input : config.server: ip:port,ip:port...");
			}

			nodes.add(new HostAndPort(st[0], Integer.parseInt(st[1])));
		}

		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(redisConf.getMaxIdle());
		poolConfig.setMaxTotal(redisConf.getMaxTotal());
//		poolConfig.setMinIdle(redisConf.getMinIdle());
//		poolConfig.setMaxWaitMillis((long) config.getTimeout());
		String managerType = "default";// config.getManagerType();
//		if (StringUtils.isEmpty(managerType)) {
//			managerType = "default";
//		}

		this.clientKey = managerType;
//		if (config.getTest()) {
//			poolConfig.setTestOnBorrow(true);
//			poolConfig.setTestOnReturn(true);
//			poolConfig.setTestOnCreate(true);
//			poolConfig.setTestOnBorrow(true);
//		}

		JedisCluster jedisCluster = new JedisCluster(nodes, this.connectionTimeout, this.soTimeout, this.maxAttempts,
				redisConf.getRedisPassword(), poolConfig);
		this.jedisCluster = jedisCluster;

	}

	protected Object getJedis() {
		return jedisCluster;
	}

	@Override
	public void expire(String key, int times) {
		((JedisCluster) getJedis()).expire(key, times);
	}

	@Override
	public boolean setnx(String key, String value, int duration) {
//		String result = ((JedisCluster) getJedis()).set(key, value, "nx", "ex", (long) duration);
		String result = ((JedisCluster) getJedis()).set(key, value, SetParams.setParams().nx().ex(duration));

		return "OK".equalsIgnoreCase(result);
	}

	@Override
	public Long del(String... keys) {
		return ((JedisCluster) getJedis()).del(keys);
	}

	@Override
	public String get(String key) {
		return ((JedisCluster) getJedis()).get(key);
	}
}
