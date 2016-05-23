package com.wch.mq.rpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by weichunhe on 2016/5/23.
 */

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    public Object rpc() {
        System.out.println("rpc client");
        return userService.findUserByIdAndName("11", "wch");
    }
}