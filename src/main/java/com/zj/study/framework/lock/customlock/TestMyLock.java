package com.zj.study.framework.lock.customlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class TestMyLock {

	public void test() {
		final Lock lock = new ObjectLock("123");
		class Worker extends Thread {
			public void run() {
				while (true) {
					lock.lock();
					try {
						try {
							TimeUnit.SECONDS.sleep(1);
							System.out.println(Thread.currentThread().getName() + "++获取到锁");
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} finally {
						lock.unlock();
					}
					try {
						TimeUnit.SECONDS.sleep(2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}

		for (int i = 0; i < 10; i++) {
			Worker w = new Worker();
			w.setDaemon(true);
			w.start();
		}

		for (int i = 0; i < 10; i++) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println();
		}

	}

	public static void main(String[] args) {
		TestMyLock testMyLock = new TestMyLock();
		testMyLock.test();
	}

}
