import demo.HelloWorldService;
import demo.SerializedException;
import org.apache.thrift.TException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by weichunhe on 2016/7/4.
 */
public class HelloWorldImpl implements HelloWorldService.Iface {
    @Override
    public String sayHello(String username) throws TException {
        if (username.equals("vf")) {
            throw new SerializedException("vf is invalid!");
        }
        return "你好," + username;
    }
}
