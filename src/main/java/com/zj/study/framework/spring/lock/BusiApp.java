package com.zj.study.framework.spring.lock;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.zj.study.framework.lock.GoodsInfo;
import com.zj.study.framework.lock.GoodsService;

public class BusiApp {
	static final int readWriteRatio = 10; // 读写线程的比例
	static final int minthreadCount = 3; // 最少线程数

	private static class GetThread implements Runnable {
		private GoodsService goodsService;

		public GetThread(GoodsService goodsService) {
			this.goodsService = goodsService;
		}

		@Override
		public void run() {
			long start = System.currentTimeMillis();
			for (int i = 0; i < 100; i++) {
				goodsService.getNum();
			}
			System.out.println(
					Thread.currentThread().getName() + "读取商品数据耗时:" + (System.currentTimeMillis() - start) + "ms");
		}

	}

	private static class SetThread implements Runnable {
		private GoodsService goodsService;

		public SetThread(GoodsService goodsService) {
			this.goodsService = goodsService;
		}

		@Override
		public void run() {
			long start = System.currentTimeMillis();

			Random r = new Random();
			for (int i = 0; i < 10; i++) {
				try {
					TimeUnit.MILLISECONDS.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				goodsService.setNum(r.nextInt(10));
			}
			System.out.println(
					Thread.currentThread().getName() + "写取商品数据耗时:" + (System.currentTimeMillis() - start) + "ms");
		}

	}

	public static void main(String[] args) {
		GoodsInfo goodsInfo = new GoodsInfo("Cup", 100000, 100000);
		// GoodsService goodsService = new UseSyn(goodsInfo);
		GoodsService goodsService = new UseRedisLock(goodsInfo);

		for (int i = 0; i < minthreadCount; i++) {
			Thread setT = new Thread(new SetThread(goodsService));
			for (int j = 0; j < readWriteRatio; j++) {
				Thread getT = new Thread(new GetThread(goodsService));
				getT.start();
			}
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setT.start();
		}
	}

}
