package org.wch.eureka.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by weichunhe on 2016/6/21.
 */
@RestController
public class EurekaClientController {
    @Autowired
    RemoteHelloService remoteHelloService;

    @RequestMapping("hello")
    public String hello() {
        return "hello eureka";
    }

    @HystrixCommand(fallbackMethod = "timeout", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")
    })
    @RequestMapping("remoteHello")
    public Object remoteHello() {
        System.out.println("hello");
        return remoteHelloService.remoteHello();
    }

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("ribbonHello")
    public String ribbonHello() {
        return restTemplate.getForEntity("http://test-service/hello", String.class).getBody();
    }
}
