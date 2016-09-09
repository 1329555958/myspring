package org.wch.aop.processers;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author weichunhe
 *         Created on 2016/9/9.
 * @version 1.0
 */
@Component
public class MyBeanFactoryPostProcesser implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("beafactory init end -------------------------------------");
    }
}
