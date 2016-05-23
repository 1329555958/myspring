package com.wch.jsonrpc.rpcimpl;

import com.wch.jsonrpc.domain.User;
import com.wch.jsonrpc.server.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by weichunhe on 2016/5/23.
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * 查找用户
     *
     * @param id
     * @param name
     * @return
     */
    @Override
    public User findUserByIdAndName(String id, String name) {
        System.out.println("rpc server");
        User user = new User();
        user.setName(name);
        user.setId(id);
        user.setAge(25);
        return user;
    }
}
