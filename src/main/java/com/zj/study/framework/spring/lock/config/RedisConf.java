package com.zj.study.framework.spring.lock.config;

public class RedisConf {
	private String server;

	private String port;

	private String redisPassword;

	private int mainIndex;

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

}
