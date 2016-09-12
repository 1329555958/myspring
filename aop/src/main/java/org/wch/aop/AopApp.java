package org.wch.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.boot.autoconfigure.test.ImportAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.wch.aop.beans.BigCat;
import org.wch.aop.beans.Cat;

/**
 * @author weichunhe
 *         Created on 2016/9/5.
 * @version 1.0
 */
@SpringBootApplication
@ImportAutoConfiguration(GsonAutoConfiguration.class)
public class AopApp {
    public static void main(String[] args) {

        SpringApplication.run(AopApp.class);
    }


    @Bean(name = "cat")
    public Cat cat() {

        Cat cat = new BigCat();
        cat.setName("overvied");
        return cat;
    }
}
