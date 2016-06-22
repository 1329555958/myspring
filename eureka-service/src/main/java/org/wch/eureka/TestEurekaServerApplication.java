package org.wch.eureka;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TestEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestEurekaServerApplication.class, args);
    }
}