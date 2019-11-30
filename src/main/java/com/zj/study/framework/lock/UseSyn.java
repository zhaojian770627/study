package com.zj.study.framework.lock;

import java.util.concurrent.TimeUnit;

public class UseSyn implements GoodsService {

	private GoodsInfo goodsInfo;

	public UseSyn(GoodsInfo goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	@Override
	public synchronized GoodsInfo getNum() {
		try {
			TimeUnit.MILLISECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.goodsInfo;
	}

	@Override
	public synchronized void setNum(int number) {
		try {
			TimeUnit.MILLISECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		goodsInfo.changeNumber(number);
	}

}
