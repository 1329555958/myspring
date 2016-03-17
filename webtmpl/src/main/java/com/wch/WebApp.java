package com.wch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

/**
 * Created by weichunhe on 2016/3/14.
 */
@SpringBootApplication
@RestController
public class WebApp {
    //判断是否可以从post中直接获取数据
    // formData 和 requestPayload的区别
    @RequestMapping(value = "/" ,method = RequestMethod.POST)
    @ResponseBody
    public String home(@RequestParam String name) {
        return "Hello " + (name == null ? "world" : name);

    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebApp.class, args);
    }
}
