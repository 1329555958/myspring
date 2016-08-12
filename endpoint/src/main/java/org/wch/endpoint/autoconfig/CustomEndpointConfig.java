package org.wch.endpoint.autoconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wch.endpoint.endpoint.Content;
import org.wch.endpoint.endpoint.CustomEndpoint;

@Configuration
public class CustomEndpointConfig {

    @Bean
    public CustomEndpoint endpoint() {
        return new CustomEndpoint();
    }
}
