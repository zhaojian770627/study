package com.zj.study.framework.spring.lock.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;

import com.zj.study.framework.spring.lock.mdd.redis.IRedisClient;
import com.zj.study.framework.spring.lock.mdd.redis.RedisClient;
import com.zj.study.framework.spring.lock.mdd.redis.RedisClusterClient;
import com.zj.study.framework.spring.lock.mdd.redis.RedisSentinelClient;

public class LockBuilder {

	private static final String REDIS_SCHEME = "redis://";

	public static RedissonClient BuildRedissonClient(RedisConf conf) {
		RedissonClient redisson = null;
		Config config = new Config();
		if ((null != conf.getCluster()) && (conf.getCluster().booleanValue())) {
			ClusterServersConfig useClusterConfig = config.useClusterServers();
			useClusterConfig.setScanInterval(2000);
			useClusterConfig.setPassword(conf.getRedisPassword());

			String[] hostStrs = conf.getServer().split(",");
			String[] hosts = hostStrs;
			int hosutCount = hostStrs.length;
			for (int pos = 0; pos < hosutCount; ++pos) {
				String hostStr = hosts[pos];
				String[] st = hostStr.split(":");
				if (st.length != 2) {
					throw new IllegalArgumentException("Illegal input : config.server: ip:port,ip:port...");
				}
				useClusterConfig.addNodeAddress(REDIS_SCHEME + st[0] + ":" + st[1]);
			}
			redisson = Redisson.create(config);
		} else if ((null != conf.getSentinel()) && (conf.getSentinel().booleanValue())) {
			SentinelServersConfig useSentinelServers = config.useSentinelServers();
			useSentinelServers.setMasterName(conf.getMasterName());
			useSentinelServers.setPassword(conf.getRedisPassword());

			String[] hostStrs = conf.getServer().split(",");
			String[] hosts = hostStrs;
			int hosutCount = hostStrs.length;
			for (int pos = 0; pos < hosutCount; ++pos) {
				String hostStr = hosts[pos];
				String[] st = hostStr.split(":");
				if (st.length != 2) {
					throw new IllegalArgumentException("Illegal input : config.server: ip:port,ip:port...");
				}
				useSentinelServers.addSentinelAddress(REDIS_SCHEME + st[0] + ":" + st[1]);
			}
			redisson = Redisson.create(config);
		} else {
			String address = REDIS_SCHEME + conf.getServer() + ":" + conf.getPort();
			config.useSingleServer().setAddress(address).setPassword(conf.getRedisPassword())
					.setDatabase(conf.getMainIndex());
			redisson = Redisson.create(config);
		}

		return redisson;
	}

	public static IRedisClient BuildRedisClient(RedisConf conf) {
		IRedisClient redisClient;
		if ((null != conf.getCluster()) && (conf.getCluster().booleanValue())) {
			redisClient = new RedisClusterClient(conf);
		} else if ((null != conf.getSentinel()) && (conf.getSentinel().booleanValue())) {
			redisClient = new RedisSentinelClient(conf);
		} else {
			redisClient = new RedisClient(conf);
		}
		return redisClient;
	}
}
