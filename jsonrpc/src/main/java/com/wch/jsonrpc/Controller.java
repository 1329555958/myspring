package com.wch.jsonrpc;

import com.wch.jsonrpc.feign.FeignInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weichunhe on 2016/7/8.
 */
@RestController
public class Controller {

    @Autowired
    private FeignInf feignInf;

    @RequestMapping(value = "/json")
    public Object testJson() {
        Map map = new HashMap();
        map.put("contentType", "application/json");
        return map;
    }

    @RequestMapping("/feignHello")
    public String hello() {
        return feignInf.feignHello();
    }
}
