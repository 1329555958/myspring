package org.wch.rpc.client.rpcservice;

import com.googlecode.jsonrpc4j.JsonRpcService;
import com.netfinworks.cloud.rpc.RpcService;
import com.wch.jsonrpc.domain.User;

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
    public User findUserByName(String name);

}
