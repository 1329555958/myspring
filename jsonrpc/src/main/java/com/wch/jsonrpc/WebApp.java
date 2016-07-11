package com.wch.jsonrpc;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcClientProxyCreator;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceExporter;
import com.wch.jsonrpc.server.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by weichunhe on 2016/3/17.
 */
@Configuration
@EnableAutoConfiguration
@SpringBootApplication
@PropertySource("classpath:application-server.properties")
@EnableEurekaClient
public class WebApp {
    public static void main(String[] args) {
        SpringApplication.run(WebApp.class, args);
    }

    @Bean
    public AutoJsonRpcServiceExporter autoJsonRpcServiceExporter() {
        return new AutoJsonRpcServiceExporter();
    }

//    @Bean
//    public AutoJsonRpcServiceImplExporter autoJsonRpcServiceImplExporter() {
//        return new AutoJsonRpcServiceImplExporter();
//    }
//
//    @Bean
//    public UserServiceImpl userService(){
//        return new UserServiceImpl();
//    }

    /**
     * 在调用的工程里添加这个配置就可以了,不使用ribbon负载均衡时这么配置
     *
     * @return
     */
//    @Bean
//    public AutoJsonRpcClientProxyCreator autoJsonRpcClientProxyCreator() {
//        AutoJsonRpcClientProxyCreator clientProxyCreator = new AutoJsonRpcClientProxyCreator();
//        URL baseUrl = null;
//        try {
//            baseUrl = new URL("http://localhost:8080");
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        clientProxyCreator.setBaseUrl(baseUrl);
//        clientProxyCreator.setScanPackage(UserService.class.getPackage().getName());
//        return clientProxyCreator;
//    }

    /**
     * 使用负载均衡时这么配置
     * @return
     */
//    @Bean
//    public static AutoJsonRpcClientProxyCreator clientProxyCreator() {
//        AutoJsonRpcClientProxyCreator creator = new AutoJsonRpcClientProxyCreator();
//        creator.setScanPackage(UserService.class.getPackage().getName());
//        creator.setServiceId("rpc-service");
//        return creator;
//    }
}
