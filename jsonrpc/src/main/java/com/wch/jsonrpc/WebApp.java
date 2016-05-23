package com.wch.jsonrpc;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceExporter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by weichunhe on 2016/3/17.
 */
@Configuration
@EnableAutoConfiguration
@SpringBootApplication
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
     * 在调用的工程里添加这个配置就可以了
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
}
