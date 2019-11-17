package com.zj.study.framework.lock;

import java.util.concurrent.TimeUnit;

public class SynchronizedIns {

	private static class MethodLock implements Runnable {
		SynchronizedIns insLock;

		public MethodLock(SynchronizedIns insLock) {
			this.insLock = insLock;
		}

		public SynchronizedIns getInsLock() {
			return insLock;
		}

		public void setInsLock(SynchronizedIns insLock) {
			this.insLock = insLock;
		}

		@Override
		public void run() {
			try {
				System.out.println(insLock);
				insLock.m1();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private static class ClassLock implements Runnable {
		SynchronizedIns insLock;

		public ClassLock(SynchronizedIns insLock) {
			this.insLock = insLock;
		}

		public SynchronizedIns getInsLock() {
			return insLock;
		}

		public void setInsLock(SynchronizedIns insLock) {
			this.insLock = insLock;
		}

		@Override
		public void run() {
			try {
				System.out.println(insLock);
				insLock.sm1();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		SynchronizedIns insLock1 = new SynchronizedIns();
		SynchronizedIns insLock2 = new SynchronizedIns();

		ClassLock lockIns1 = new ClassLock(insLock1);
		ClassLock lockIns2 = new ClassLock(insLock2);
		Thread t1 = new Thread(lockIns1);
		Thread t2 = new Thread(lockIns2);

		t1.start();
		t2.start();
	}

	// 普通方法
	private synchronized void m1() throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " m1 methon begin");
		TimeUnit.SECONDS.sleep(5);
		System.out.println(Thread.currentThread().getName() + " m1 methon end");
	}

	private synchronized void m2() throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " m2 methon begin");
		TimeUnit.SECONDS.sleep(5);
		System.out.println(Thread.currentThread().getName() + " m2 methon end");
	}

	private static synchronized void sm1() throws InterruptedException {
		System.out.println("sm1 methon begin");
		TimeUnit.SECONDS.sleep(5);
		System.out.println("sm1 methon end");
	}

}
