package com.zj.study.framework.spring.lock;

import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;

import org.redisson.Redisson;
import org.redisson.api.RReadWriteLock;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zj.study.framework.spring.lock.config.MainConfig;

public class BusiApp2 {
	static CountDownLatch countDownLatch = new CountDownLatch(1);

	private static class GetThread implements Runnable {
		private RReadWriteLock lock;

		public GetThread(RReadWriteLock lock) {
			this.lock = lock;
		}

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + "开始加读锁");
			boolean b = lock.readLock().tryLock(); // 读锁
			if (!b) {
				System.out.println(Thread.currentThread().getName() + "获取读锁失败");
				return;
			}
			System.out.println(Thread.currentThread().getName() + "获得读锁");

			try {
				System.out.println(Thread.currentThread().getName() + "---执行业务逻辑开始--");
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName() + "---执行业务逻辑结束--");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			lock.readLock().unlock();
		}

	}

	private static class SetThread implements Runnable {

		private RReadWriteLock lock;

		public SetThread(RReadWriteLock lock) {
			this.lock = lock;
		}

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + "开始加写锁");
			lock.writeLock().lock(); // 写锁
			System.out.println(Thread.currentThread().getName() + "获得写锁");
			countDownLatch.countDown();
			try {
				System.out.println(Thread.currentThread().getName() + "---执行业务逻辑开始--");
				Thread.sleep(5000);
				System.out.println(Thread.currentThread().getName() + "---执行业务逻辑结束--");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			lock.writeLock().unlock();
		}

	}

	public static void main(String[] args) throws IOException, InterruptedException, BrokenBarrierException {
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
		Redisson redissonClient = (Redisson) app.getBean("redissonClient");
		RReadWriteLock lock = redissonClient.getReadWriteLock("aaaa");

		SetThread wt = new SetThread(lock);
		new Thread(wt).start();

		countDownLatch.await();

		for (int i = 0; i < 10; i++) {
			GetThread rt = new GetThread(lock);
			new Thread(rt).start();
		}
	}

}
