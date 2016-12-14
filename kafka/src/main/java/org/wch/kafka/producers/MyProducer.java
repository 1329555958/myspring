package org.wch.kafka.producers;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author weichunhe
 *         Created on 2016/12/6.
 * @version 1.0
 */
public class MyProducer {
    public static void newInstance() {
        Properties props = new Properties();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.5.16.3:9092");

        props.put(ProducerConfig.ACKS_CONFIG, "all");

        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        long start = System.currentTimeMillis();
        Producer<String, String> producer = new KafkaProducer<>(props);
        long cost = System.currentTimeMillis() - start;
        System.out.println("创建生产者花费时间:" + cost);
        for (int i = 0; i < 100; i++) {
            producer.send(new ProducerRecord<String, String>("kafka-test", Integer.toString(i), Integer.toString(i)));
        }
        System.out.println("花费:" + (System.currentTimeMillis() - start - cost));
        producer.close();
    }

    public static void main(String[] args) {
        newInstance();
    }
}
