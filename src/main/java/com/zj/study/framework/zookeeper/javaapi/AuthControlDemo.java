package com.zj.study.framework.zookeeper.javaapi;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

public class AuthControlDemo implements Watcher {
	private final static String CONNECTSTRING = "10.6.255.181:2181";
	private static CountDownLatch countDownLatch = new CountDownLatch(1);
	private static CountDownLatch countDownLatch2 = new CountDownLatch(1);

	private static ZooKeeper zookeeper;
	private static Stat stat = new Stat();

	public static void main(String[] args) throws Exception {
		zookeeper = new ZooKeeper(CONNECTSTRING, 5000, new AuthControlDemo());
		countDownLatch.await();

		ACL acl = new ACL(ZooDefs.Perms.ALL,
				new Id("digest", DigestAuthenticationProvider.generateDigest("root:root")));
		ACL acl2 = new ACL(ZooDefs.Perms.CREATE, new Id("ip", "192.168.1.1"));
		
		
		
	}

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
