package com.wch.jsonrpc.rpcimpl;

import com.wch.jsonrpc.domain.User;
import com.wch.jsonrpc.rpcservice.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by weichunhe on 2016/5/23.
 */
@Service
public class UserServiceImpl2 implements UserService {


    @Override
    public User findUserByName(String name) {
        System.out.println("rpc server 2");
        User user = new User();
        user.setName(name);
        user.setId(System.currentTimeMillis() + "");
        user.setAge(30);
        return user;
    }
}
