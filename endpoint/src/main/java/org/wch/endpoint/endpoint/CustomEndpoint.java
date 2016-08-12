package org.wch.endpoint.endpoint;

import org.springframework.beans.BeansException;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author weichunhe
 *         Created on 2016/8/12.
 */
@ConfigurationProperties(prefix = "endpoints.service", ignoreUnknownFields = false)
public class CustomEndpoint extends AbstractEndpoint<Content> implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    public CustomEndpoint() {
        super("service");
    }

    @Override
    public Content invoke() {
        return applicationContext.getBean(Content.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
