package com.zj.study.framework.lock.customlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import com.zj.study.framework.lock.Tools;

public class TestObjectLock {

	public void test() {
		final Lock lock1 = new ObjectLock("1");
		final Lock lock2 = new ObjectLock("1");
		class Worker extends Thread {
			public void run() {
				int r = Tools.getRandomInt(2);
				System.out.println("r:" + r);
				Lock lock;
				if (r == 0)
					lock = lock1;
				else
					lock = lock2;

				while (true) {
					lock.lock();
					try {
						try {
							TimeUnit.SECONDS.sleep(1);
							System.out.println(Thread.currentThread().getName() + "++获取到lock" + (r + 1) + "锁");
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
		TestObjectLock testMyLock = new TestObjectLock();
		testMyLock.test();
	}

}
