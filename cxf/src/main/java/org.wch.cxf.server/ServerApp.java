package org.wch.cxf.server;

import com.vf.agent.util.LogChain;
import com.vf.agent.util.TraceChain;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;
import org.wch.cxf.impl.UserServiceImpl;
import org.wch.cxf.service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

//    @Bean
//    public FilterRegistrationBean loginFilter() {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setName("logChain");
//        LogChainFilter filter = new LogChainFilter();
//        registrationBean.setFilter(filter);
//        registrationBean.setOrder(1);
//
//
//        List<String> patterns = new ArrayList<>();
//        patterns.add("/*");
//        registrationBean.setUrlPatterns(patterns);
//
//        return registrationBean;
//    }

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

/**
 * 日志链
 */
class LogChainFilter implements Filter {
    private static Logger log = LoggerFactory.getLogger(LogChainFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String id = req.getHeader("chain-id");
        if (!StringUtils.isEmpty(id)) {
            Thread.currentThread().setName(id);
        }
        String traceId = req.getHeader("trace-id");
        TraceChain.startTrace(traceId);
        log.info("chain-id={},traceId={}", id, traceId);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}

