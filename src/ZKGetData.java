import java.io.*;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
public class ZKGetData {
	private static ZooKeeper zk;
	   private static ZookeeperConnection conn;
	   public static Stat znode_exists(String path) throws 
	      KeeperException,InterruptedException {
	      return zk.exists(path,true);
	   }

	   public static void main(String[] args) throws InterruptedException, KeeperException {
	      String path = "/MyFirstZnode";
	      final CountDownLatch connectedSignal = new CountDownLatch(1);
			
	      try {
	         conn = new ZookeeperConnection();
	         zk = conn.connect("localhost");
	         Stat stat = znode_exists(path);
				
	         if(stat != null) {
	            byte[] b = zk.getData(path, new Watcher() {
					
	               public void process(WatchedEvent we) {
						
	                  if (we.getType() == Event.EventType.None) {
	                     switch(we.getState()) {
	                        case Expired:
	                        connectedSignal.countDown();
	                        break;
	                     }
								
	                  } else {
	                     String path = "/MyFirstZnode";
								
	                     try {
	                        byte[] bn = zk.getData(path,
	                        false, null);
	                        String data = new String(bn,
	                        "UTF-8");
	                        System.out.println(data);
	                        connectedSignal.countDown();
								
	                     } catch(Exception ex) {
	                        System.out.println(ex.getMessage());
	                     }
	                  }
	               }
	            }, null);
					
	            String data = new String(b, "UTF-8");
	            System.out.println(data);
	            connectedSignal.await();
					
	         } else {
	            System.out.println("Node does not exists");
	         }
	      } catch(Exception e) {
	        System.out.println(e.getMessage());
	      }
	   }
}
