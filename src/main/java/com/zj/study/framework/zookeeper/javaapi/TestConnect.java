package com.zj.study.framework.zookeeper.javaapi;

import java.io.IOException;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class TestConnect implements Watcher {

	private final static String CONNECTSTRING = "localhost:2181";

	public static void main(String[] args) throws IOException {
		ZooKeeper zooKeeper = new ZooKeeper(CONNECTSTRING, 5000, new TestConnect());
		System.in.read();
	}

	@Override
	public void process(WatchedEvent event) {
		System.out.println(event.toString());
	}

}
