package com.zj.study.framework.spring.lock;

import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

import com.zj.study.framework.lock.Tools;

public class BusiApp3 {
	static AtomicInteger var = new AtomicInteger(0);

	private static class SetThreadZero extends Thread {
		Thread otherThread;

		public SetThreadZero() {
		}

		public Thread getOtherThread() {
			return otherThread;
		}

		public void setOtherThread(Thread otherThread) {
			this.otherThread = otherThread;
		}

		@Override
		public void run() {
			while (true) {
				if (var.compareAndSet(0, 1)) {
					System.out.println("SetThreadZero do something");
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					var.set(0);
					LockSupport.unpark(otherThread);
					try {
						TimeUnit.MILLISECONDS.sleep(Tools.getRandomInt(10));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					LockSupport.park();
				}
			}
		}

	}

	private static class SetThreadOne extends Thread {
		Thread otherThread;

		public Thread getOtherThread() {
			return otherThread;
		}

		public void setOtherThread(Thread otherThread) {
			this.otherThread = otherThread;
		}

		public SetThreadOne() {
		}

		@Override
		public void run() {
			while (true) {
				if (var.compareAndSet(0, 1)) {
					System.out.println("SetThreadOne do something");
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					var.set(0);
					LockSupport.unpark(otherThread);
					try {
						TimeUnit.MILLISECONDS.sleep(Tools.getRandomInt(10));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					LockSupport.park();
				}
			}
		}

	}

	public static void main(String[] args) throws IOException, InterruptedException, BrokenBarrierException {
		SetThreadZero zero = new SetThreadZero();
		SetThreadOne one = new SetThreadOne();

		zero.setOtherThread(one);
		one.setOtherThread(zero);
		zero.start();
		one.start();
	}

}
