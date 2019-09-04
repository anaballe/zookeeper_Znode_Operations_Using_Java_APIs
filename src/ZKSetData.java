import java.io.*;
import org.apache.zookeeper.*;
public class ZKSetData {
	   private static ZooKeeper zk;
	   private static ZookeeperConnection conn;

	   // Method to update the data in a znode. Similar to getData but without watcher.
	   public static void update(String path, byte[] data) throws
	      KeeperException,InterruptedException {
	      zk.setData(path, data, zk.exists(path,true).getVersion());
	   }

	   public static void main(String[] args) throws InterruptedException,KeeperException, IOException {
		  InputStreamReader imp = new InputStreamReader(System.in);
		  BufferedReader br = new BufferedReader(imp);
		  System.out.println("Enter the znode you wish to set data to ");
		  String p = br.readLine();
	      String path= "/"+p;
	      System.out.println("Set the new data you wish to place to this znode");
	      String v = br.readLine();
	      byte[] data = v.getBytes(); //Assign data which is to be updated.
			
	      try {
	         conn = new ZookeeperConnection();
	         zk = conn.connect("localhost");
	         update(path, data); // Update znode data to the specified path
	      } catch(Exception e) {
	         System.out.println(e.getMessage());
	      }
	   }

}
