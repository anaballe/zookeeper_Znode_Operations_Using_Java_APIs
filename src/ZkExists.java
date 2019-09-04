import java.io.*;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
public class ZkExists {
	private static ZooKeeper zk;
	private static ZookeeperConnection conn;
	public static Stat znode_exists(String path)throws KeeperException,InterruptedException {
		
		return zk.exists(path,true);
	}
	public static void main(String[] args)throws KeeperException,InterruptedException, IOException {
		InputStreamReader imp = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(imp);
		System.out.println("Enter the node to check for");
		String p = br.readLine();
		String path = "/" + p;
		try {
			conn = new ZookeeperConnection();
			zk = conn.connect("localhost");
			Stat stat = znode_exists(path);
			if(stat != null) {
				System.out.println("Node exists and the node version is "+stat.getVersion());
				String v = stat.toString();
				System.out.println(v);
			}
			else {
				System.out.println("Node does not exists");
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
