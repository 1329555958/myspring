package org.wch.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.ObjectError;

/**
 * Created by weichunhe on 2016/7/14.
 */
@SpringBootApplication
public class ClientApp {
    public static void main(String[] args) {
        SpringApplication.run(ClientApp.class);
    }

    @Bean
    public Object inject(@Value("${all.shared:noShared}") String shared, @Value("${higher.precedence:pre}") String
            precedence, @Value("${server.override:no}") String override) {
        System.out.println("共享application:" + shared);
        System.out.println("优先级:" + precedence);
        System.out.println("覆盖:" + override);
        return new Object();
    }
}
