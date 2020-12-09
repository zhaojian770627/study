package com.zj.study.framework.spring.lock.redisson;

import java.util.HashMap;
import java.util.Map;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RKeys;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zj.study.framework.spring.lock.config.MainConfig;

public class RedissonMainTest {

	public static void main(String[] args) throws InterruptedException {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		Redisson redissonClient = (Redisson) app.getBean("redissonClient");

//		keys(redissonClient);
		buckets(redissonClient);

	}

	private static void buckets(Redisson redisson) {
		Map<String, String> m = new HashMap<>();
		m.put("a", "aaaaaa");
		m.put("b", "bbbbbb");
		m.put("c", "cccccc");
		RBucket<Map<String, String>> bucket = redisson.getBucket("zj");
		bucket.set(m);
		Map<String, String> map = bucket.get();
		System.out.println(map.size());
//		bucket.trySet(new AnyObject(3));
//		bucket.compareAndSet(new AnyObject(4), new AnyObject(5));
//		bucket.getAndSet(new AnyObject(6));
	}

	private static void keys(Redisson redissonClient) {
		RKeys keys = redissonClient.getKeys();
		Iterable<String> allKeys = keys.getKeys();
		allKeys.forEach(s -> {
			System.out.println(s);
		});
		System.err.println("-----------------------------------------------------------");
		Iterable<String> foundedKeys = keys.getKeysByPattern("action_*");
		foundedKeys.forEach(s -> {
			System.out.println(s);
		});
	}

}
