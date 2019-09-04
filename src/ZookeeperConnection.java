import java.io.*;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event.KeeperState;
public class ZookeeperConnection {
	private ZooKeeper zoo;
	final CountDownLatch connectedSignal= new CountDownLatch(1);
	public ZooKeeper connect(String host)throws IOException,InterruptedException{
		zoo = new ZooKeeper(host,5000,new Watcher() {
			public void process(WatchedEvent we) {
				if(we.getState() == KeeperState.SyncConnected) {
					connectedSignal.countDown();
				}
			}
		});
		return zoo;
		
	}
	public void close()throws InterruptedException{
		zoo.close();
	}
	
}
