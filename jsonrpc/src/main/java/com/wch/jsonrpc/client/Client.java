package com.wch.jsonrpc.client;

import com.wch.jsonrpc.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by weichunhe on 2016/5/23.
 */
@RestController
@RequestMapping("/client")
public class Client {

    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    public Object rpc() {
        return userService.findUserByIdAndName("11", "wch");
    }
}
