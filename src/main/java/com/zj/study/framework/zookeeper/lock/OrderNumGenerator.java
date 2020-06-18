package com.zj.study.framework.zookeeper.lock;

import java.util.concurrent.TimeUnit;

public class OrderNumGenerator {
	// 区分不同的订单号
	private static int count = 0;

	// 单台服务器，多个线程 同事生成订单号
	public String getNumber() {
		try {
			TimeUnit.SECONDS.sleep(60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "" + ++count; // 时间戳后面加了 count
	}

}