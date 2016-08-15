package com.wch.jsonrpc.rpcimpl;

import com.wch.jsonrpc.domain.User;
import com.wch.jsonrpc.domain.UserInf;
import com.wch.jsonrpc.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        User user = new User();//service2.findUserByName(name);
        user.setName("name=" + name);
        user.setId("id=" + id);
        return user;
    }

    public User findUser(int age) {
        System.out.println("rpc service 1");
        User user = new User();//service2.findUserByName(name);
        user.setName("wch333");
        user.setId("123333");
        user.setAge(age);
        return user;
    }

    public String getName(String name) {
        return name;
    }

    public String getName(UserInf user) {
        return user.getName();
    }

    public List<String> getName(List<User> users) {
        List<String> names = new ArrayList<>();
        users.forEach(u -> names.add(u.getName()));
        return names;
    }

    public List<String> getNames(List<String> names) {
        return names;
    }
}
