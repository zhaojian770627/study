package com.zj.study.framework.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class UseCountLatch {
	static CountDownLatch countDownLatch = new CountDownLatch(5);

	private static class SubTask implements Runnable {
		CountDownLatch latch;

		public SubTask(CountDownLatch latch) {
			this.latch = latch;
		}

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getId() + " begin run");
			try {
				TimeUnit.SECONDS.sleep(Tools.getRandomInt(3));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getId() + " work complete");
			latch.countDown();
		}

	}

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 5; i++) {
			new Thread(new SubTask(countDownLatch)).start();
		}

		countDownLatch.await();
		System.out.println("Main " + " work begin");
	}

}
