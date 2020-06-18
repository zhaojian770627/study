package com.zj.study.framework.zookeeper.lock;

public class OrderNumGenerator {
	// 区分不同的订单号
	private static int count = 0;

	// 单台服务器，多个线程 同事生成订单号
	public String getNumber() {
		return "" + ++count; // 时间戳后面加了 count
	}

}