package com.zj.study.framework.lock.delay;

import java.util.concurrent.DelayQueue;

public class FetchOrder implements Runnable {

	private DelayQueue<ItemVO<Order>> queue;

	public FetchOrder(DelayQueue<ItemVO<Order>> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				ItemVO<Order> item = queue.take();
				Order order = (Order) item.getData();
				System.out.println("get from queue:" + order.getOrderNo());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
