package org.wch.cxf.client;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wch.cxf.bean.User;
import org.wch.cxf.service.UserService;

/**
 * @author weichunhe
 *         Created on 2016/12/2.
 * @version 1.0
 */
@SpringBootApplication
@RestController
public class ClientApp {
    static Logger log = LoggerFactory.getLogger(ClientApp.class);

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(ClientApp.class).profiles("client").run(args);
    }

    @RequestMapping("/user/{userId}")
    public User getUser(@PathVariable long userId) {
        log.info("获取用户,userid={}", userId);
        return userService.getUser(userId);
    }

    @Bean
    public UserService userServicess() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
//注册WebService接口
        factory.setServiceClass(UserService.class);
//设置WebService地址
        factory.setAddress("http://localhost:9000/soap/user");
        return (UserService) factory.create();
    }
}
