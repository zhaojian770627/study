package com.zj.study.framework.spring.lock.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zj.study.framework.spring.lock.mdd.LockService;

//配置类====配置文件
@Configuration()
@PropertySource(value = "classpath:/redis.properties")
public class MainConfig {

//	@Bean
	public LockService lockService() {
		return new LockService();
	}

	@Bean
	public RedisConf redisConf() {
		return new RedisConf();
	}

	@Bean
	public RedissonClient redissonClient(@Autowired RedisConf conf) {
		Config config = new Config();
		String address = "redis://" + conf.getServer() + ":" + conf.getPort();
		config.useSingleServer().setAddress(address).setPassword(conf.getRedisPassword()).setDatabase(6);
		RedissonClient redisson = Redisson.create(config);
		return redisson;
	}
}
