package org.wch.eureka.controller;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by weichunhe on 2016/6/22.
 */
@FeignClient("test-service")
public interface RemoteHelloService {
    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String remoteHello();
}
