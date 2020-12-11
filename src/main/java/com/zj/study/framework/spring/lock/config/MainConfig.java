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
public class MainConfig {
	private final String server = "172.20.56.145";

	private final String port = "6379";

	private final String redisPassword = "Yonyou@1988";

	@Bean
	public RedissonClient redissonClient() {
		Config config = new Config();
		String address = "redis://" + server + ":" + port;
		config.useSingleServer().setAddress(address).setPassword(redisPassword).setDatabase(6);
		RedissonClient redisson = Redisson.create(config);
		return redisson;
	}

	@Bean
	public RedisClient redisClient() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(500);
		config.setMaxIdle(5);
		config.setMaxWaitMillis(100);
		config.setTestOnBorrow(true);
		JedisPool pool = new JedisPool(config, server, Integer.valueOf(port), 100000, redisPassword);
		RedisClient jedisUtil = new RedisClient(pool, 6);
		return jedisUtil;
	}
}
