package org.wch.eureka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class RemoteHelloController {

    //Eureka会自动注入注册的所有Client信息，不过并没有啥用处
    @Autowired
    private DiscoveryClient client;

    @RequestMapping("hello")
    public String hello() {
        long start = System.currentTimeMillis();
        ServiceInstance instance = client.getLocalServiceInstance();
        // 随机睡眠1000毫秒以内
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long cost = System.currentTimeMillis() - start;
        String msg =
                "Remote Hello~ " + instance.getHost() + ", " + instance.getServiceId()
                        + ", spent " + cost;
        System.out.println(msg);
        return msg;
    }

    private static int count = 0;

    @RequestMapping("/evenerror")
    public String evenError(String name) {
        System.out.println(count + "-----");
        Assert.isTrue(count++ % 2 != 0, "even error");
        return "hello:" + name;
    }

    @RequestMapping("/service3/evenerror")
    public String service3(String name){
        return "service3 strip false:"+name;
    }
}
