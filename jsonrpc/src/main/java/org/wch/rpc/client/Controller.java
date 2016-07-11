package org.wch.rpc.client;

import com.wch.jsonrpc.domain.User;
import com.wch.jsonrpc.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weichunhe on 2016/7/8.
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class Controller {
    @Autowired
    private UserService userService;


    @RequestMapping("/user")
    public Object findUserByIdAndName(String id, String name) {
        User user = userService.findUserByIdAndName(id, name);
        System.out.println(user);
        return user;
    }

    @RequestMapping(value = "/json")
    public Object testJson() {
        User user = new User();
        user.setAge(10);
        user.setId("123");
        return user;
    }
}
