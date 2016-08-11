package com.wch.jsonrpc.rpcimpl;

import com.wch.jsonrpc.domain.User;
import com.wch.jsonrpc.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by weichunhe on 2016/5/23.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    org.wch.rpc.client.rpcservice.UserService service2;

    /**
     * 查找用户
     *
     * @param id
     * @param name
     * @return
     */
    @Override
    public User findUserByIdAndName(String id, String name) {
        System.out.println("rpc service 1");
        User user =  new User();//service2.findUserByName(name);
        user.setName("wch");
        user.setId("123");
        return user;
    }

}
