package com.wch.jsonrpc.server;

import com.googlecode.jsonrpc4j.JsonRpcService;
import com.wch.jsonrpc.domain.User;

/**
 * Created by weichunhe on 2016/5/23.
 */
@JsonRpcService("rpc/user")
public interface UserService {
    /**
     * 查找用户
     *
     * @param id
     * @param name
     * @return
     */
    public User findUserByIdAndName(String id, String name);

}
