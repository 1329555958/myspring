package org.wch.eureka;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableEurekaServer
@EnableTask
public class EurekaServerApp {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApp.class, args);
    }


    public MyTaskApplication myTask() {
        return new MyTaskApplication();
    }


    public CommandLineRunner commandLineRunner() {
        return strings ->
                System.out.println("Executed at :" +
                        new SimpleDateFormat().format(new Date()));
    }

    public static class MyTaskApplication implements CommandLineRunner {

        @Override
        public void run(String... strings) throws Exception {
            System.out.println("Hello World");
        }
    }

    @Scheduled(cron = "0,30 * * * *  ?")
    public void print() {
        System.out.println("cron");
    }
}