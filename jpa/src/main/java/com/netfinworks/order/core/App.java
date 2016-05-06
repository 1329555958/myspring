package com.netfinworks.order.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@PropertySource("file:/opt/pay/config/matrix/matrix-fmall/application.properties")
//@ImportResource(value = { "classpath:applicationContext.xml" })
@RestController
public class App {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }
}