package com.zj.study.framework.zookeeper.lock;

import java.util.concurrent.TimeUnit;

public class OrderService implements Runnable {

	private OrderNumGenerator orderNumGenerator = new OrderNumGenerator(); // 定义成全局的
	private Lock lock = new ZookeeperDistrbuteLock();

	public void run() {
		getNumber();
	}

	public synchronized void getNumber() { // 加锁 保证线程安全问题 让一个线程操作
		try {
			lock.getLock();
			String number = orderNumGenerator.getNumber();
			System.out.println(Thread.currentThread().getName() + ",number" + number);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unLock();
		}
	}

	public static void main(String[] args) throws InterruptedException {
//		OrderService orderService = new OrderService();
		for (int i = 0; i < 100; i++) { // 开启100个线程
			// 模拟分布式锁的场景
			new Thread(new OrderService()).start();
		}

		TimeUnit.SECONDS.sleep(60);
	}

}