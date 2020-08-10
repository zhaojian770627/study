package com.zj.study.framework.cache;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.jmx.export.MBeanExporter;

import redis.clients.jedis.JedisPoolConfig;

@Configurable
//@EnableCaching
@ComponentScan("com.zj.study.framework.cache")
public class MainConfig {
	@Bean
	public MBeanExporter mbeanExporter(SimpleBean simpleBean) {
		MBeanExporter exporter = new MBeanExporter();
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("zj:name=cacheFacade", simpleBean);
		exporter.setBeans(beans);
		return exporter;
	}

	@Bean
	public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
		return RedisCacheManager.builder(connectionFactory)
				.cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(20))) // 缓存时间绝对过期时间20s
				.transactionAware().build();
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);

		// 使用jdk的序列化
		template.setValueSerializer(new JdkSerializationRedisSerializer());
		// 使用StringRedisSerializer来序列化和反序列化redis的key值
		template.setKeySerializer(new StringRedisSerializer());
		template.afterPropertiesSet();
		return template;
	}

	@Bean
	public RedisConnectionFactory connectionFactory() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setTestOnBorrow(true);
		poolConfig.setTestOnReturn(false);
		poolConfig.setTestWhileIdle(true);
		JedisClientConfiguration clientConfig = JedisClientConfiguration.builder().usePooling().poolConfig(poolConfig)
				.and().readTimeout(Duration.ofMillis(1000)).build();

		// 单点redis
		RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
		// 哨兵redis
		// RedisSentinelConfiguration redisConfig = new RedisSentinelConfiguration();
		// 集群redis
		// RedisClusterConfiguration redisConfig = new RedisClusterConfiguration();
		redisConfig.setHostName("192.168.3.11");
		redisConfig.setPassword(RedisPassword.of("123456a"));
		redisConfig.setPort(6379);
		redisConfig.setDatabase(0);

		return new JedisConnectionFactory(redisConfig, clientConfig);
	}
}
