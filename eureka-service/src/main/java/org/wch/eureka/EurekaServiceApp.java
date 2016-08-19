package org.wch.eureka;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.sleuth.stream.SleuthStreamAutoConfiguration;

@SpringBootApplication
@EnableEurekaClient
public class EurekaServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServiceApp.class, args);
    }
}