import demo.HelloWorldService;
import org.apache.thrift.TException;

/**
 * Created by weichunhe on 2016/7/4.
 */
public class HelloWorldImpl implements HelloWorldService.Iface {
    @Override
    public String sayHello(String username) throws TException {
        return "你好," + username;
    }
}
