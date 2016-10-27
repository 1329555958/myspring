package org.wch.logagent;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weichunhe
 *         Created on 2016/10/20.
 * @version 1.0
 */
@SpringBootApplication
public class AgentApp {
    private static Logger log = LoggerFactory.getLogger(AgentApp.class);

    public static void main(String[] args) {
        System.out.println("tid=" + Thread.currentThread().getName());

        log.info("app started at {}", System.currentTimeMillis());
        SpringApplication.run(AgentApp.class);
    }

    @Bean
    public FilterRegistrationBean loginFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("logChain");
        LogChainFilter filter = new LogChainFilter();
        registrationBean.setFilter(filter);
        registrationBean.setOrder(1);


        List<String> patterns = new ArrayList<>();
        patterns.add("/*");
        registrationBean.setUrlPatterns(patterns);

        return registrationBean;
    }

}

/**
 * 日志链
 */
class LogChainFilter implements Filter {

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
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
