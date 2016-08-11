package com.wch.jsonrpc.client;

import com.googlecode.jsonrpc4j.JsonRpcClient;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;
import com.wch.jsonrpc.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by weichunhe on 2016/5/23.
 */
public class Client {

    public static void main(String[] args) {
        JsonRpcHttpClient client = null;
        try {
            client = new JsonRpcHttpClient(new URL("http://localhost:10010/jsonrpc/com/wch/jsonrpc/server/UserService"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        UserService service = ProxyUtil.createClientProxy(client.getClass().getClassLoader(), UserService.class, client);
        System.out.println(service.findUserByIdAndName("1", "wch"));
    }
}
