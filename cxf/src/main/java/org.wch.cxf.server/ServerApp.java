package org.wch.cxf.server;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.wch.cxf.impl.UserServiceImpl;
import org.wch.cxf.service.UserService;


/**
 * @author weichunhe
 *         Created on 2016/12/2.
 * @version 1.0
 */
@SpringBootApplication
public class ServerApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder().profiles("server").sources(ServerApp.class).run(args);
//        SpringApplication.run(ServerApp.class, args);
    }


    @Bean
    public ServletRegistrationBean dispatcherServlet() {
        return new ServletRegistrationBean(new CXFServlet(), "/soap/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public javax.xml.ws.Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), userService());
        endpoint.publish("/user");
        return endpoint;
    }
}


