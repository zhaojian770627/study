package com.zj.study.framework.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class UseCyclicBarrier {
	static CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new CollectTask());

	private static class CollectTask implements Runnable {

		@Override
		public void run() {
			System.out.println("CollectTask begin run");
		}

	}

	private static class SubTask implements Runnable {
		CyclicBarrier latch;

		public SubTask(CyclicBarrier latch) {
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
			System.out.println(Thread.currentThread().getId() + " One Phase work complete");
			try {
				latch.await();
			} catch (InterruptedException e) {
			} catch (BrokenBarrierException e) {
			}
			System.out.println(Thread.currentThread().getId() + " Tow Phase work complete");
		}

	}

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 5; i++) {
			new Thread(new SubTask(cyclicBarrier)).start();
		}

		System.out.println("Main " + " work begin");
	}

}
