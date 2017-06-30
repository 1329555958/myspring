package com.wch.mq;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcClientProxyCreator;
import com.wch.mq.rpc.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by weichunhe on 2016/3/14.
 */
@SpringBootApplication
@EnableScheduling
public class WebApp {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebApp.class, args);
    }

    @Bean
    public AutoJsonRpcClientProxyCreator autoJsonRpcClientProxyCreator() {
        AutoJsonRpcClientProxyCreator clientProxyCreator = new AutoJsonRpcClientProxyCreator();
        URL baseUrl = null;
        try {
            baseUrl = new URL("http://localhost:8080");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        clientProxyCreator.setBaseUrl(baseUrl);
        clientProxyCreator.setScanPackage(UserService.class.getPackage().getName());
        return clientProxyCreator;
    }
}
