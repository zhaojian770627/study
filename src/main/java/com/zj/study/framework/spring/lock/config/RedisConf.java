package com.zj.study.framework.spring.lock.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RedisConf {
	@Value("${redis.server}")
	private String server;

	@Value("${redis.port}")
	private String port;

	@Value("${redis.password}")
	private String redisPassword;

	@Value("${redis.mainIndex}")
	private int mainIndex;

	private String masterName;

	private int maxIdle = 10;
	private int minIdle = 10;
	private int maxTotal = 100;
	private long maxWaitMillis;

	private Integer timeout;

	@Value("${redis.cluster}")
	private Boolean isCluster;
	
	private Boolean isSentinel;

	public static RedisConf setConf() {
		return new RedisConf();
	}

	public String getServer() {
		return server;
	}

	public RedisConf setServer(String server) {
		this.server = server;
		return this;
	}

	public String getPort() {
		return port;
	}

	public RedisConf setPort(String port) {
		this.port = port;
		return this;
	}

	public String getRedisPassword() {
		return redisPassword;
	}

	public RedisConf setRedisPassword(String redisPassword) {
		this.redisPassword = redisPassword;
		return this;
	}

	public int getMainIndex() {
		return mainIndex;
	}

	public RedisConf setMainIndex(int mainIndex) {
		this.mainIndex = mainIndex;
		return this;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public int getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}

	public long getMaxWaitMillis() {
		return maxWaitMillis;
	}

	public void setMaxWaitMillis(long maxWaitMillis) {
		this.maxWaitMillis = maxWaitMillis;
	}

	public Boolean getCluster() {
		return isCluster;
	}

	public void setCluster(Boolean cluster) {
		this.isCluster = cluster;
	}

	public Boolean getSentinel() {
		return isSentinel;
	}

	public void setSentinel(Boolean sentinel) {
		this.isSentinel = sentinel;
	}

	public Integer getTimeout() {
		if (timeout == null) {
			return Integer.valueOf(2000);
		}
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public String getMasterName() {
		return masterName;
	}

	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}
}
