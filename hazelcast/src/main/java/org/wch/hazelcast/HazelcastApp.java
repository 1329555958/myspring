package org.wch.hazelcast;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.hazelcast.HazelcastKeyValueAdapter;
import org.springframework.data.hazelcast.repository.config.EnableHazelcastRepositories;
import org.springframework.data.keyvalue.core.KeyValueOperations;
import org.springframework.data.keyvalue.core.KeyValueTemplate;
import org.wch.hazelcast.topic.Sample;

import java.util.HashMap;

/**
 * @author weichunhe
 *         Created on 2016/9/1.
 * @version 1.0
 */

@SpringBootApplication
@EnableHazelcastRepositories("org.wch.hazelcast.repository")
public class HazelcastApp extends SpringBootServletInitializer {
    public static void main(String[] args) {
        Sample.main(args);
//        SpringApplication.run(HazelcastApp.class);
    }

    @Bean
    HazelcastInstance hazelcastInstance() {
//        return Hazelcast.newHazelcastInstance();
        return HazelcastClient.newHazelcastClient();
    }

    @Bean
    public KeyValueOperations keyValueTemplate(HazelcastInstance instance) {
        return new KeyValueTemplate(new HazelcastKeyValueAdapter(instance));
    }

    @Bean
    public HazelcastKeyValueAdapter hazelcastKeyValueAdapter(HazelcastInstance hazelcastInstance) {
        return new HazelcastKeyValueAdapter(hazelcastInstance);
    }
}
