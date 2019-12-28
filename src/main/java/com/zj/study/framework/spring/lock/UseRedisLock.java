package com.zj.study.framework.spring.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import org.redisson.Redisson;
import org.redisson.api.RReadWriteLock;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zj.study.framework.lock.GoodsInfo;
import com.zj.study.framework.lock.GoodsService;
import com.zj.study.framework.spring.lock.config.MainConfig;

public class UseRedisLock implements GoodsService {

	private GoodsInfo goodsInfo;
	AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);
	Redisson redissonClient = (Redisson) app.getBean("redissonClient");
	RReadWriteLock lock = redissonClient.getReadWriteLock("aaaa");
	private final Lock getLock = lock.readLock();// 读锁
	private final Lock setLock = lock.writeLock();// 写锁

	public UseRedisLock(GoodsInfo goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	@Override
	public GoodsInfo getNum() {
		getLock.lock();
		try {
			TimeUnit.MILLISECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			getLock.unlock();
		}
		return this.goodsInfo;
	}

	@Override
	public void setNum(int number) {
		setLock.lock();
		try {
			TimeUnit.MILLISECONDS.sleep(5);
			goodsInfo.changeNumber(number);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			setLock.unlock();
		}
	}

}
