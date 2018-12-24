package zookeeper;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooKeeper;

public class Client implements Watcher {
	static ZooKeeper zk = null;

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		zk = new ZooKeeper("localhost:2181", 5000, new Client());
		zk.exists("/project", true);
		Thread.sleep(Integer.MAX_VALUE);
	}

	@Override
	public void process(WatchedEvent e) {
		if (e.getType().equals(EventType.NodeDataChanged)) {
			System.out.println(e.getPath() + " NodeDataChange!");
			try {
				zk.exists(e.getPath(), true);
			} catch (KeeperException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
