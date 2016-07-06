package org.wch.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wch.annotation.annotation.BeanScannerConfigurer;
import org.wch.annotation.annotation.ScanClass;

@Configuration
public class CustomizeScanTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(CustomizeScanTest.class);
        annotationConfigApplicationContext.register(BeanScannerConfigurer.class);
        annotationConfigApplicationContext.refresh();
        ScanClass injectClass = annotationConfigApplicationContext.getBean(ScanClass.class);
        injectClass.print();
    }
}
