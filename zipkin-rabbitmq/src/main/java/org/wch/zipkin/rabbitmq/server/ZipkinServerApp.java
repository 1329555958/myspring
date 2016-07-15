package org.wch.zipkin.rabbitmq.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;
import org.springframework.context.annotation.Profile;

/**
 * Created by weichunhe on 2016/7/15.
 */
@SpringBootApplication
@EnableZipkinStreamServer
public class ZipkinServerApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder().profiles("server").sources(ZipkinServerApp.class)
                .run(args);
    }
}
