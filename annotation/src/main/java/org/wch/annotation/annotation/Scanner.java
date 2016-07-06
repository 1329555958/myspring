package org.wch.annotation.annotation;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Set;

public final class Scanner extends ClassPathBeanDefinitionScanner {
    public Scanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public void registerDefaultFilters() {
        this.addIncludeFilter(new AnnotationTypeFilter(MyAnnotation.class));
    }

    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
        for (BeanDefinitionHolder holder : beanDefinitions) {
            GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();
            System.out.println(definition.getBeanClassName());
            definition.getPropertyValues().add("innerClassName", definition.getBeanClassName());
            definition.setBeanClass(FactoryBeanTest.class);
        }
        return beanDefinitions;
    }

    public boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return super.isCandidateComponent(beanDefinition) && beanDefinition.getMetadata()
                .hasAnnotation(MyAnnotation.class.getName());
    }
}
