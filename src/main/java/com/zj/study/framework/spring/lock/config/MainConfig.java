package com.zj.study.framework.spring.lock.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;
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
		config.useSingleServer().setAddress(address).setPassword(conf.getRedisPassword())
				.setDatabase(conf.getMainIndex());
		RedissonClient redisson = Redisson.create(config);
		return redisson;
	}

	@Bean
	public DataSource getDataSource(@Value("${jdbc.user}") String user, @Value("${jdbc.password}") String password,
			@Value("${jdbc.driverClass}") String driverClass, @Value("${jdbc.jdbcUrl}") String jdbcUrl)
			throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser(user);
		dataSource.setPassword(password);
		dataSource.setDriverClass(driverClass);
		dataSource.setJdbcUrl(jdbcUrl);
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(@Autowired DataSource dataSource) throws PropertyVetoException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);
		return jdbcTemplate;
	}
}
