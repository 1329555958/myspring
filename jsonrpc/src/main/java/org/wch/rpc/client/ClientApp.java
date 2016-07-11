package org.wch.rpc.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcClientProxyCreator;
import com.netfinworks.util.jsonrpc.MatrixJsonProxyFactoryBean;
import com.wch.jsonrpc.domain.User;
import com.wch.jsonrpc.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URL;


@SpringBootApplication
@PropertySource("application-client.properties")
@EnableEurekaClient
public class ClientApp {

    public static void main(String[] args) {
        SpringApplication.run(ClientApp.class);
    }

    @Bean
    public static AutoJsonRpcClientProxyCreator clientProxyCreator() {
        AutoJsonRpcClientProxyCreator creator = new AutoJsonRpcClientProxyCreator();
        creator.setScanPackage(UserService.class.getPackage().getName());
        creator.setServiceId("rpc-service");
        return creator;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
