package com.zj.study.framework.spring.lock;

import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BusiApp4 {
	static Object lock = new Object();
	static AtomicInteger var = new AtomicInteger(0);

	private static class SetThreadZero extends Thread {
		@Override
		public void run() {
			while (true) {

				synchronized (lock) {
					try {
						if (var.get() == 0) {
							System.out.println("SetThreadZero begin-----------------");
							TimeUnit.SECONDS.sleep(1);
							var.set(1);
							lock.notify();
						} else
							lock.wait();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}

			}
		}

	}

	private static class SetThreadOne extends Thread {
		public SetThreadOne() {
		}

		@Override
		public void run() {
			while (true) {
				synchronized (lock) {
					try {
						if (var.get() == 1) {
							System.out.println("SetThreadOne begin-----------------");
							TimeUnit.SECONDS.sleep(1);
							var.set(0);
							lock.notify();
						} else
							lock.wait();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
		}

	}

	public static void main(String[] args) throws IOException, InterruptedException, BrokenBarrierException {
		SetThreadZero zero = new SetThreadZero();
		SetThreadOne one = new SetThreadOne();

		zero.start();
		one.start();
	}

}
