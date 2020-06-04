package com.zj.study.framework.zookeeper.javaapi;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ApiOperatorDemo implements Watcher {
	private final static String CONNECTSTRING = "10.6.255.181:2181";
	private static CountDownLatch countDownLatch = new CountDownLatch(1);

	private static ZooKeeper zookeeper;
	private static Stat stat = new Stat();

	public static void main(String[] args) throws Exception {
		zookeeper = new ZooKeeper(CONNECTSTRING, 5000, new ApiOperatorDemo());
		countDownLatch.await();

//		createNode();
//		modifyNode();
//		deleteNode();
		createNodeAndChild();
	}

	private static void createNodeAndChild() throws Exception {
		// 创建节点和子节点
		String path = "/node11";

		zookeeper.create(path, "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		TimeUnit.SECONDS.sleep(1);

		Stat stat = zookeeper.exists(path + "/node1", true);
		if (stat == null) {// 表示节点不存在
			zookeeper.create(path + "/node1", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			TimeUnit.SECONDS.sleep(1);
		}
		// 修改子路径
		zookeeper.setData(path + "/node1", "deer".getBytes(), -1);
		TimeUnit.SECONDS.sleep(1);
	}

	private static void deleteNode() throws Exception {
		zookeeper.getData("/node1", new ApiOperatorDemo(), stat);
		zookeeper.delete("/node1", -1);
		Thread.sleep(2000);
	}

	private static void modifyNode() throws KeeperException, InterruptedException {
		// 修改数据
		zookeeper.getData("/node1", new ApiOperatorDemo(), stat);
		zookeeper.setData("/node1", "deer2".getBytes(), -1);
		Thread.sleep(2000);
	}

	private static void createNode() throws KeeperException, InterruptedException {
		String result = zookeeper.create("/node2", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
				CreateMode.PERSISTENT);
		zookeeper.getData("/node1", new ApiOperatorDemo(), stat); // 增加一个
		System.out.println("创建成功：" + result);
	}

	@Override
	public void process(WatchedEvent watchedEvent) {
		// 如果当前的连接状态是连接成功的，那么通过计数器去控制
		if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
			if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
				countDownLatch.countDown();
				System.out.println(watchedEvent.getState() + "-->" + watchedEvent.getType());
			} else if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {
				try {
					System.out.println("数据变更触发路径：" + watchedEvent.getPath() + "->改变后的值："
							+ new String(zookeeper.getData(watchedEvent.getPath(), true, stat)));
				} catch (KeeperException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {// 子节点的数据变化会触发
				try {
					System.out.println("子节点数据变更路径：" + watchedEvent.getPath() + "->节点的值："
							+ zookeeper.getData(watchedEvent.getPath(), true, stat));
				} catch (KeeperException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else if (watchedEvent.getType() == Event.EventType.NodeCreated) {// 创建子节点的时候会触发
				try {
					System.out.println("节点创建路径：" + watchedEvent.getPath() + "->节点的值："
							+ zookeeper.getData(watchedEvent.getPath(), true, stat));
				} catch (KeeperException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else if (watchedEvent.getType() == Event.EventType.NodeDeleted) {// 子节点删除会触发
				System.out.println("节点删除路径：" + watchedEvent.getPath());
			}
		}
	}

}
