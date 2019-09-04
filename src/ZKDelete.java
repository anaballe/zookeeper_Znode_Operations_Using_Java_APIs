import java.io.*;
import org.apache.zookeeper.*;
public class ZKDelete {
	private static ZooKeeper zk;
	   private static ZookeeperConnection conn;

	   // Method to check existence of znode and its status, if znode is available.
	   public static void delete(String path) throws KeeperException,InterruptedException {
	      zk.delete(path,zk.exists(path,true).getVersion());
	   }
	   public static void main(String[] args) throws InterruptedException,KeeperException, IOException {
		  InputStreamReader imp = new InputStreamReader(System.in);
		  BufferedReader br = new BufferedReader(imp);
		  System.out.println("Enter the path of the znode which you want to delete");
		  String p = br.readLine();
	      String path = "/" + p;
	      try {
	         conn = new ZookeeperConnection();
	         zk = conn.connect("localhost");
	         delete(path); //delete the node with the specified path
	      } catch(Exception e) {
	         System.out.println(e.getMessage()); // catches error messages
	      }
	   }
}
