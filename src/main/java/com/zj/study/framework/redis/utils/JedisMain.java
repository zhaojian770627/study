package com.zj.study.framework.redis.utils;

public class JedisMain {

	public static void main(String[] args) {
		JedisUtils jedisUtil = new JedisUtils();
		String zj = jedisUtil.get("zj");
		System.out.println(zj);
	}

}
