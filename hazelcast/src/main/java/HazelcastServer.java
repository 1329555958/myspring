import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.wch.hazelcast.proxy.MyClientListener;

/**
 * @author weichunhe
 *         Created on 2017/4/19.
 * @version 1.0
 */
public class HazelcastServer {
    public static void main(String[] args){
        HazelcastInstance server = Hazelcast.newHazelcastInstance();
        server.getClientService().addClientListener(new MyClientListener());
    }
}
