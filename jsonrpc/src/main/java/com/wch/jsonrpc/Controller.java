package com.wch.jsonrpc;

import com.wch.jsonrpc.domain.User;
import com.wch.jsonrpc.domain.UserInf;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weichunhe on 2016/7/8.
 */
@RestController
public class Controller {
    @RequestMapping(value = "/json")
    public Object testJson() {
        Map map = new HashMap();
        map.put("contentType", "application/json");
        return map;
    }

    @RequestMapping(value = "/hello/{name}")
    public Object pathValue(@PathVariable String name, Integer age) {
        return "hello," + name + ";you are " + age;
    }

    @RequestMapping("/user")
    public Object user(User user) {
        return user.getName();
    }
}
