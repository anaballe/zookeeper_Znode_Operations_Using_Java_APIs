import java.io.*;
import org.apache.zookeeper.*;
public class ZnodeCreate {
	private static ZooKeeper zk;
	private static ZookeeperConnection conn;
	public static void create(String path,byte[] data)throws KeeperException,InterruptedException{
		zk.create(path,data,ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
	}
	public static void main(String[] args)throws IOException {
		InputStreamReader imp = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(imp);
		System.out.println("Enter the path you want to create the znode\n");
		String p = br.readLine();
		String path = "/"+p;
		System.out.println("Enter the data you want inside this znode\n");
		String k = br.readLine();
		byte[] data = k.getBytes();
		try {
			conn = new ZookeeperConnection();
			zk = conn.connect("127.0.0.1");
			create(path,data);
			conn.close();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
