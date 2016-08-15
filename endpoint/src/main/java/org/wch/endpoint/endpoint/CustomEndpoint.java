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
public class CustomEndpoint extends AbstractEndpoint<Content> {


    public CustomEndpoint() {
        super("service");
    }

    @Override
    public Content invoke() {
        Content content = new Content();
        content.getServices().add("sdfasdf");
        return content;
    }


}
