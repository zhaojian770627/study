package com.zj.study.framework.lock.delay;

public class Order {
	private final String orderNo;
	private final double orderMoney;

	public Order(String orderNo, double orderMoney) {
		this.orderNo = orderNo;
		this.orderMoney = orderMoney;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public double getOrderMoney() {
		return orderMoney;
	}

}
