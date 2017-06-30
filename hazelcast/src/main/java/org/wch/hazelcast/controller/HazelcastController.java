package org.wch.hazelcast.controller;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wch.hazelcast.Bean.User;
import org.wch.hazelcast.proxy.MyLifecycleListener;
import org.wch.util.JSONUtil;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @author weichunhe
 *         Created on 2016/9/1.
 * @version 1.0
 */
@RestController
@RequestMapping("/hazelcast")
public class HazelcastController {

    static Logger log = LoggerFactory.getLogger(HazelcastController.class);
    @Autowired
    HazelcastInstance instance;

    private IMap<String, User> map;

    String mapName = "UserMap";

    @PostConstruct
    public void init() {
        map = instance.getMap(mapName);
    }

    @RequestMapping("/_health_check")
    public String health() {
        return "OK";
    }

    @RequestMapping("/user/save/{name}")
    public Object save(@PathVariable String name) {
        User user = new User(UUID.randomUUID().toString(), 1, new Date());
        map.put(user.getName(), user);
        log.info("save ---{}", user.getName());
        return user;
    }

    @RequestMapping("/user/qry/{name}")
    public Object qry(@PathVariable String name) {
        User user = map.get(name);
        log.info("qry {}---{}", name, user);
        return user;
    }

    @RequestMapping("/clear")
    public int clean() {
        int size = map.size();

        Iterator<String> it = map.keySet().iterator();
        String key = it.next();
        map.clear();
        log.info("clear map size={}", size);
        return size;
    }

    public static void main(String[] args){
        Random random = new  Random();
        System.out.println(random.nextInt(2));

    }
}
