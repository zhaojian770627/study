package com.zj.study.framework.zookeeper.javaapi;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class AuthControlDemo implements Watcher {
	private final static String CONNECTSTRING = "10.6.255.181:2181";
	private static CountDownLatch countDownLatch = new CountDownLatch(1);
	private static CountDownLatch countDownLatch2 = new CountDownLatch(1);

	private static ZooKeeper zookeeper;
	private static Stat stat = new Stat();

	@Override
	public void process(WatchedEvent watchedEvent) {
		// 如果当前的连接状态是连接成功的，那么通过计数器去控制
		if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
			if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
				countDownLatch.countDown();
				System.out.println(watchedEvent.getState() + "-->" + watchedEvent.getType());
			}
		}

	}
}
