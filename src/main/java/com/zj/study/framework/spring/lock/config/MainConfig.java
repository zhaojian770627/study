package com.zj.study.framework.spring.lock.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//配置类====配置文件
@Configuration
public class MainConfig {
	private final String server = "10.10.10.5";

	private final String port = "6379";

	private final String redisPassword = "123456a";

	@Bean
	public RedissonClient redissonClient() {
		Config config = new Config();
		String address = "redis://" + server + ":" + port;
		config.useSingleServer().setAddress(address).setPassword(redisPassword).setDatabase(6);
		RedissonClient redisson = Redisson.create(config);
		return redisson;
	}
}
