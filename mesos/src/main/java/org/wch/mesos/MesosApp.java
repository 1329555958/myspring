package org.wch.mesos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author weichunhe
 *         Created on 2016/12/23.
 * @version 1.0
 */
@SpringBootApplication
@RestController
public class MesosApp extends SpringBootServletInitializer {

    static List<String> cache = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(MesosApp.class, args);
    }


    @RequestMapping("/hello")
    public String hello(String name) {
        return "hello " + Optional.ofNullable(name).orElse("world");
    }

    @RequestMapping("/cache/{key}/{length}")
    public int addCache(@PathVariable String key, @PathVariable int length) {
        while (length-- > 0) {
            cache.add(key);
        }
        return cache.size();
    }

    @RequestMapping("/cache/clean")
    public int clean() {
        cache.clear();
        return cache.size();
    }

    @RequestMapping("/health")
    public String health() {
        return "success";
    }

}
