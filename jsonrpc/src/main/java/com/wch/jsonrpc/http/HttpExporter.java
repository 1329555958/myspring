package com.wch.jsonrpc.http;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author weichunhe
 *         Created on 2016/8/25.
 * @version 1.0
 */
@Component
public class HttpExporter implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) beanFactory;
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(HttpHandler.class);
        defaultListableBeanFactory.registerBeanDefinition("/my/http", builder.getBeanDefinition());
    }
}
