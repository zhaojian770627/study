package com.zj.study.framework.redis.utils;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zj.study.framework.redis.config.MainConfig;

public class JedisMain {

	public static void main(String[] args) {
//		JedisUtils jedisUtil = new JedisUtils();
//		String zj = jedisUtil.get("zj");
//		System.out.println(zj);

		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		System.out.println(app.getBean(JedisUtils.class).get("zj"));
	}

}
