package org.wch.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.wch.annotation.annotation.MyComponent;

import javax.annotation.PostConstruct;

@Configuration
public class ComponentAnnotationTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(ComponentAnnotationTest.class);
        annotationConfigApplicationContext.refresh();
        InjectClass injectClass = annotationConfigApplicationContext.getBean(InjectClass.class);
        injectClass.print();
    }

    @MyComponent
    public static class InjectClass {
        public void print() {
            System.out.println("hello world");
        }

        @PostConstruct
        public void init() {
            System.out.println("init");
        }
    }
}