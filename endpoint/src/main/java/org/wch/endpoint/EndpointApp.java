package org.wch.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wch.endpoint.endpoint.Content;

import java.util.List;

/**
 * @author weichunhe
 *         Created on 2016/8/12.
 */
@SpringBootApplication
@RestController
public class EndpointApp {
    public static void main(String[] args) {
        SpringApplication.run(EndpointApp.class);
    }

    @Autowired
    private Content content;

    @RequestMapping("/addservice")
    public List service(String service) {
        content.getServices().add(service);
        return content.getServices();
    }

}
