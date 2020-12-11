package com.zj.study.framework.spring.lock.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zj.study.framework.spring.lock.mdd.redis.RedisClient;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

//配置类====配置文件
@Configuration
public class LockBuilder {

	public static RedissonClient BuildRedissonClient(RedisConf conf) {
		Config config = new Config();
		String address = "redis://" + conf.getServer() + ":" + conf.getPort();
		config.useSingleServer().setAddress(address).setPassword(conf.getRedisPassword())
				.setDatabase(conf.getMainIndex());
		RedissonClient redisson = Redisson.create(config);
		return redisson;
	}

	public static RedisClient BuildRedisClient(RedisConf conf) {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(500);
		config.setMaxIdle(5);
		config.setMaxWaitMillis(100);
		config.setTestOnBorrow(true);
		JedisPool pool = new JedisPool(config, conf.getServer(), Integer.valueOf(conf.getPort()), 100000,
				conf.getRedisPassword());
		RedisClient jedisUtil = new RedisClient(pool, conf.getMainIndex());
		return jedisUtil;
	}
}
