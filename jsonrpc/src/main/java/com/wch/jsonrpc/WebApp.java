package com.wch.jsonrpc;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcClientProxyCreator;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceExporter;
import com.netfinworks.cloud.rpc.Util;
import com.netfinworks.cloud.rpc.endpoint.ServiceContent;
import com.netfinworks.cloud.rpc.spring.AutoRpcClientProxyCreator;
import com.netfinworks.cloud.rpc.spring.AutoRpcServiceExporter;
import com.wch.jsonrpc.domain.User;
import com.wch.jsonrpc.domain.UserInf;
import com.wch.jsonrpc.rpcimpl.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.sleuth.zipkin.ZipkinSpanReporter;
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
    public AutoRpcServiceExporter autoJsonRpcServiceExporter() {
        User user = new User();
        user.setName("jsonrpc");
        Util.addBean4Inf(UserInf.class, user);
        AutoRpcServiceExporter.addImplScanPackage(UserServiceImpl.class.getName());
        return new AutoRpcServiceExporter();
    }


    @Bean
    public static AutoRpcClientProxyCreator clientProxyCreator2() {
        AutoRpcClientProxyCreator creator = new AutoRpcClientProxyCreator();
        creator.setScanPackage(org.wch.rpc.client.rpcservice.UserService.class.getPackage().getName());
        creator.setServiceId("rpc-client");
        return creator;
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
     *
     * @return
     */
//    @Bean
//    public static AutoJsonRpcClientProxyCreator clientProxyCreator() {
//        AutoJsonRpcClientProxyCreator creator = new AutoJsonRpcClientProxyCreator();
//        creator.setScanPackage(UserService.class.getPackage().getName());
//        creator.setServiceId("rpc-service");
//        return creator;
//    }

    // Use this for debugging (or if there is no Zipkin server running on port 9411)
    @Bean
    @ConditionalOnProperty(value = "sample.zipkin.enabled", havingValue = "false")
    public ZipkinSpanReporter spanCollector() {
        System.out.println("sample.zipkin.enabled");
        return new ZipkinSpanReporter() {
            @Override
            public void report(zipkin.Span span) {
                System.out.println((String.format("Reporting span [%s]", span)));
            }
        };
    }
}
