package com.zj.study.framework.cache;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jmx.export.MBeanExporter;

@Configurable
@EnableCaching
@ComponentScan("com.zj.study.framework.cache")
public class MainConfig {
	@Bean
	public MBeanExporter mbeanExporter(FacadeBean simpleBean) {
		MBeanExporter exporter = new MBeanExporter();
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("zj:name=cacheFacade", simpleBean);
		exporter.setBeans(beans);
		return exporter;
	}

//	@Bean
//	public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
//		return RedisCacheManager.builder(connectionFactory)
//				.cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(20))) // 缓存时间绝对过期时间20s
//				.transactionAware().build();
//	}

	@Bean
	public CacheManager cacheManager() {
		// configure and return an implementation of Spring's CacheManager SPI
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("province")));
		return cacheManager;
	}

//	@Bean
//	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
//		RedisTemplate<String, Object> template = new RedisTemplate<>();
//		template.setConnectionFactory(factory);
//
//		// 使用jdk的序列化
//		template.setValueSerializer(new JdkSerializationRedisSerializer());
//		// 使用StringRedisSerializer来序列化和反序列化redis的key值
//		template.setKeySerializer(new StringRedisSerializer());
//		template.afterPropertiesSet();
//		return template;
//	}
//
//	@Bean
//	public RedisConnectionFactory connectionFactory() {
//		JedisPoolConfig poolConfig = new JedisPoolConfig();
//		poolConfig.setTestOnBorrow(true);
//		poolConfig.setTestOnReturn(false);
//		poolConfig.setTestWhileIdle(true);
//		JedisClientConfiguration clientConfig = JedisClientConfiguration.builder().usePooling().poolConfig(poolConfig)
//				.and().readTimeout(Duration.ofMillis(1000)).build();
//
//		// 单点redis
//		RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
//		// 哨兵redis
//		// RedisSentinelConfiguration redisConfig = new RedisSentinelConfiguration();
//		// 集群redis
//		// RedisClusterConfiguration redisConfig = new RedisClusterConfiguration();
//		redisConfig.setHostName("10.10.10.4");
//		redisConfig.setPassword(RedisPassword.of("123456a"));
//		redisConfig.setPort(6379);
//		redisConfig.setDatabase(0);
//
//		return new JedisConnectionFactory(redisConfig, clientConfig);
//	}
}
