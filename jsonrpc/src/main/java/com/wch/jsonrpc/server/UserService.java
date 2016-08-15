package com.wch.jsonrpc.server;

import com.googlecode.jsonrpc4j.JsonRpcService;
import com.netfinworks.cloud.rpc.RpcService;
import com.wch.jsonrpc.domain.User;
import com.wch.jsonrpc.domain.UserInf;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weichunhe on 2016/5/23.
 */
@RpcService
public interface UserService {
    /**
     * 查找用户
     *
     * @param id
     * @param name
     * @return
     */
    public User findUserByIdAndName(String id, String name);

    public User findUser(int age);

    public String getName(String name);

    public String getName(UserInf user);

    public List<String> getName(List<User> users);

    public List<String> getNames(List<String> names);
}
