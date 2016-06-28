package org.wch.stream.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.sleuth.zipkin.ZipkinSpanReporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application-producer.properties")
public class StreamProducerApp {
    public static void main(String[] args) {
        SpringApplication.run(StreamProducerApp.class, args);
    }

    // Use this for debugging (or if there is no Zipkin server running on port 9411)
    @Bean
    @ConditionalOnProperty(value = "sample.zipkin.enabled", havingValue = "false")
    public ZipkinSpanReporter spanCollector() {
        System.out.println("sample.zipkin.enabled");
        return new ZipkinSpanReporter() {
            @Override
            public void report(zipkin.Span span) {
                System.out.println((String.format("Reporting span [%s]", span)));
            }
        };
    }
}