import java.io.*;
import java.util.List;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
public class ZKGetChildren {
	private static ZooKeeper zk;
	   private static ZookeeperConnection conn;

	   // Method to check existence of znode and its status, if znode is available.
	   public static Stat znode_exists(String path) throws 
	      KeeperException,InterruptedException {
	      return zk.exists(path,true);
	   }

	   public static void main(String[] args) throws InterruptedException,KeeperException, IOException {
	      InputStreamReader imp = new InputStreamReader(System.in);
	      BufferedReader br = new BufferedReader(imp);
	      System.out.println("Enter the znode whose children you wish to find");
		  String p = br.readLine();
		  String path = "/" + p;
	      try {
	         conn = new ZookeeperConnection();
	         zk = conn.connect("localhost");
	         Stat stat = znode_exists(path); // Stat checks the path

	         if(stat!= null) {

	            //“getChildren” method- get all the children of znode.It has two args, path and watch
	            List <String> children = zk.getChildren(path, false);
	            for(int i = 0; i < children.size(); i++)
	            System.out.println(children.get(i)); //Print children's
	         } else {
	            System.out.println("Node does not exists");
	         }

	      } catch(Exception e) {
	         System.out.println(e.getMessage());
	      }

	   }
}
