package com.zj.study.framework.lock;

import java.util.concurrent.TimeUnit;

public class SemaphoreTest {

	static ResourcePools pool = new ResourcePools();

	public static void main(String[] args) throws InterruptedException {
		int threadCount = 50;
		for (int i = 0; i < threadCount; i++) {
			Thread thread = new Thread(new Worker(), "worker_" + i);
			thread.start();
		}
	}

	static class Worker implements Runnable {
		public void run() {
			try {
				long start = System.currentTimeMillis();
				String resource = pool.fetchConn();
				System.out
						.println(Thread.currentThread().getName() + " 获取资源耗费时间 " + (System.currentTimeMillis() - start));
				TimeUnit.MILLISECONDS.sleep(100 + Tools.getRandomInt(100));
				pool.releaseConn(resource);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
