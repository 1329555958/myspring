package org.wch.hazelcast.controller;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wch.hazelcast.proxy.MyLifecycleListener;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author weichunhe
 *         Created on 2016/9/1.
 * @version 1.0
 */
@RestController
public class Controller {

    @Autowired
    ClientConfig config;

    HazelcastInstance instance34 = null;
    HazelcastInstance instance35 = null;
    IMap map34 = null;
    IMap map35 = null;

    String mapName = "largeMap2";

    @PostConstruct
    public void init() {
        ClientConfig config = new ClientConfig();
        ClientNetworkConfig networkConfig = config.getNetworkConfig();
        networkConfig.addAddress("10.5.16.14:5701");
        networkConfig.setConnectionAttemptLimit(5);
        networkConfig.setConnectionAttemptPeriod(10000);
        networkConfig.setConnectionTimeout(5000);
        instance34 = HazelcastClient.newHazelcastClient(config);
        map34 = instance34.getMap(mapName);
        config = new ClientConfig();
//        networkConfig = config.getNetworkConfig();
//        networkConfig.addAddress("10.5.16.14:5702");
        instance35 = HazelcastClient.newHazelcastClient();
        map35 = instance35.getMap(mapName);
        instance34.getLifecycleService().addLifecycleListener(new MyLifecycleListener());
    }


    @RequestMapping("/set")
    public Object set(String key, String value, @RequestParam(defaultValue = "false") boolean reset) {
        long id = System.currentTimeMillis();
        System.out.println(id + "--start");
//        System.out.println(instance34.getLifecycleService().isRunning());

        if (reset) {
            System.out.println("reset");
            init();
        }
        map34.put(key, value);
        System.out.println(id + "--end");
        return "success";
    }

    public String before() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append(i);
        }
        return sb.toString();

    }

    @RequestMapping("/batchSet")
    public Object batchSet() {
        long begin = System.currentTimeMillis();
        String value = before();
        int step = 100000;
        int start = 0 * step;
        for (int i = start; i < start + step; i++) {
            map34.put("key" + i, i + "-" + value);
        }

        return "success" + (System.currentTimeMillis() - begin);
    }


    @RequestMapping("/batchGet")
    public Object batchGet() {
        Map map = null;
        map = new HashedMap();
        long begin = System.currentTimeMillis();
        String value = before();
        int step = 100000;
        int start = 0 * step;
        for (int i = start; i < start + step; i++) {
            map.put("key" + i, map35.get("key" + i));
        }
        return "success" + (System.currentTimeMillis() - begin);

    }

    @RequestMapping("/get")
    public Object get(String key) {
        return map35.getOrDefault(key, "no-value");
    }
}
