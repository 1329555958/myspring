package org.wch.rpc.client;

import com.netfinworks.util.jsonrpc.MatrixJsonProxyFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@PropertySource("application-client.properties")
public class ClientApp {

    public static void main(String[] args) {
        SpringApplication.run(ClientApp.class);
    }

//    public MatrixJsonProxyFactoryBean
}
