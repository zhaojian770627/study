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

	// 在mills时间内还拿不到数据库连接，返回一个null
	public String fetchConn(long mills) throws InterruptedException {
		availRes.acquire();
		String result = null;
		synchronized (resourcePool) {
			result = resourcePool.removeFirst();
		}
		availPos.release();
		return result;
	}

	// 放回数据库连接
	public void releaseConn(String conn) throws InterruptedException {
		if (conn != null) {
			availPos.acquire();
			synchronized (resourcePool) {
				resourcePool.addLast(conn);
			}
			availRes.release();
		}
	}
}
