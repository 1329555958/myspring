package org.wch.mesos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author weichunhe
 *         Created on 2016/12/23.
 * @version 1.0
 */
@SpringBootApplication
@RestController
public class MesosApp extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(MesosApp.class, args);
    }


    @RequestMapping("/hello")
    public String hello(String name) {
        return "hello " + Optional.ofNullable(name).orElse("world");
    }

    @RequestMapping("/health")
    public String health() {
        return "success";
    }

}
