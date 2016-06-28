package org.wch.stream.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;

@EnableBinding(Sink.class)
public class Consumer {

    Logger log = LoggerFactory.getLogger(Consumer.class);
//    @StreamListener(Barista.OUTPUT_CHANNEL)
////    @ServiceActivator(inputChannel = Barista.OUTPUT_CHANNEL, outputChannel = Barista.OUTPUT1)
//    public String receive(String msg) {
//        System.out.println("StreamListener receive:" + msg);
//        return msg + "-sendto";
//    }

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void listener(String msg) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("receive:{}", msg);
    }

//    @ServiceActivator(inputChannel = Sink.INPUT, outputChannel = Barista.OUTPUT1)
//    public String transform(String payload) {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("ServiceActivator transform:" + payload + "->" + payload.toUpperCase());
//        return payload.toUpperCase();
//    }

}
