package com.wch.jsonrpc.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("test-client")
public interface FeignInf {

    @RequestMapping(value = "errorHello", method = RequestMethod.GET)
    public String feignHello();
}
