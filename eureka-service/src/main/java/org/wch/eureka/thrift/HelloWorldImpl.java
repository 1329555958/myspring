package org.wch.eureka.thrift;

import org.apache.thrift.TException;
import ru.trylogic.spring.boot.thrift.annotation.ThriftController;

/**
 * Created by weichunhe on 2016/7/4.
 */
@ThriftController("/thrift")
public class HelloWorldImpl implements HelloWorldService.Iface {
    @Override
    public String sayHello(String username) throws TException {
        if (username.equals("vf")) {
            throw new RuntimeException("vf is exception!");
        }
        return "你好," + username;
    }
}
