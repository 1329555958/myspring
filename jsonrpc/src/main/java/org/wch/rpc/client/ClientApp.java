package org.wch.rpc.client;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcClientProxyCreator;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceExporter;
import com.netfinworks.cloud.rpc.spring.AutoRpcClientProxyCreator;
import com.netfinworks.cloud.rpc.spring.AutoRpcServiceExporter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.wch.rpc.client.rpcservice.UserServiceImpl2;
import com.wch.jsonrpc.server.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.sleuth.zipkin.ZipkinSpanReporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@PropertySource("application-client.properties")
@EnableEurekaClient
public class ClientApp {

    public static void main(String[] args) {
        SpringApplication.run(ClientApp.class);
    }

//    @Bean
//    public AutoRpcServiceExporter autoJsonRpcServiceExporter() {
//        AutoRpcServiceExporter.addImplScanPackage(UserServiceImpl2.class.getName());
//        return new AutoRpcServiceExporter();
//    }


    /**
     *
     * @return
     */
    @Bean
    public static AutoRpcClientProxyCreator clientProxyCreator() {
        AutoRpcClientProxyCreator creator = new AutoRpcClientProxyCreator();
        creator.setScanPackage(UserService.class.getPackage().getName());
        creator.setServiceId("rpc-service");
        return creator;
    }


    // Use this for debugging (or if there is no Zipkin server running on port 9411)
    @Bean
    @ConditionalOnProperty(value = "sample.zipkin.enabled", havingValue = "false")
    public ZipkinSpanReporter spanCollector() {
        System.out.println("sample.zipkin.enabled");
        return span -> System.out.println((String.format("Reporting span [%s]", span)));
    }
}
