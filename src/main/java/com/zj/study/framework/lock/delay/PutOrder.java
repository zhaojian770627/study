package com.zj.study.framework.lock.delay;

import java.util.concurrent.DelayQueue;

public class PutOrder implements Runnable {

	private DelayQueue<ItemVO<Order>> queue;

	public PutOrder(DelayQueue<ItemVO<Order>> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		Order orderTb = new Order("Tb00001", 366);
		ItemVO<Order> itemTb = new ItemVO<Order>(5000, orderTb);
		queue.offer(itemTb);
		System.out.println("订单5秒到期:" + orderTb.getOrderNo());

		Order orderJd = new Order("Jd00001", 366);
		ItemVO<Order> itemJd = new ItemVO<Order>(8000, orderJd);
		queue.offer(itemJd);
		System.out.println("订单8秒到期:" + orderJd.getOrderNo());
	}

}
