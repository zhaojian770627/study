package com.zj.study.framework.lock;

public class GoodsInfo {
	private String name;
	private double totalMoney; // 总销售额
	private int storeNumber; // 库存数

	public GoodsInfo(String name, double totalMoney, int storeNumber) {
		super();
		this.name = name;
		this.totalMoney = totalMoney;
		this.storeNumber = storeNumber;
	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public int getStoreNumber() {
		return storeNumber;
	}

	public void changeNumber(int sellNumber) {
		this.totalMoney += sellNumber * 25;
		this.storeNumber -= sellNumber;
	}

}
