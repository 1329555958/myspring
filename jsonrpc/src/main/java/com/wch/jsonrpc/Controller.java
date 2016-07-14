package com.wch.jsonrpc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weichunhe on 2016/7/8.
 */
@RestController
public class Controller {
    @RequestMapping(value = "/json")
    public Object testJson() {
        Map map = new HashMap();
        map.put("contentType", "application/json");
        return map;
    }
}
