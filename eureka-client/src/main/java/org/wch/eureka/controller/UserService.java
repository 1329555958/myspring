package org.wch.eureka.controller;

import com.googlecode.jsonrpc4j.JsonRpcService;

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
    public String findUserByIdAndName(String id, String name);

}
