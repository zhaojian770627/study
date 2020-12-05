package com.zj.study.thread.hazelcast;

import java.util.Map;
import java.util.concurrent.Future;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import com.hazelcast.core.Member;

public class Simple {

	public static void main(String[] args) {
		HazelcastInstance hc1 = Hazelcast.newHazelcastInstance();
		HazelcastInstance hc2 = Hazelcast.newHazelcastInstance();

		hc1.getMap("testcache").put("name", "andy");
		System.out.println(hc1.getMap("testcache").get("name"));
		System.out.println(hc2.getMap("testcache").get("name"));

		IExecutorService executorService = hc1.getExecutorService("executor");
		Map<Member, Future<String>> submitToAllMembers = executorService.submitToAllMembers(new MonitorTask());
		for (String string : args) {
			
		}
	}

}
