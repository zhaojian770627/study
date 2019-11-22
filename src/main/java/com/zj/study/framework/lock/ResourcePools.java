package com.zj.study.framework.lock;

import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.Semaphore;

public class ResourcePools {
	private final static int POOL_SIZE = 10;

	private final Semaphore availRes, availPos;
	// 资源池的容器
	private static LinkedList<String> resourcePool = new LinkedList<>();

	public ResourcePools() {
		availRes = new Semaphore(POOL_SIZE);
		availPos = new Semaphore(0);
	}

	static {
		for (int i = 0; i < POOL_SIZE; i++) {
			resourcePool.addLast(UUID.randomUUID().toString());
		}
	}
}
