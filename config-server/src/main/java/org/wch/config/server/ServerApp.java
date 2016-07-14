package org.wch.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by weichunhe on 2016/7/14.
 */
@SpringBootApplication
@EnableConfigServer
public class ServerApp {
    public static void main(String[] args) {
        SpringApplication.run(ServerApp.class);
    }
}
