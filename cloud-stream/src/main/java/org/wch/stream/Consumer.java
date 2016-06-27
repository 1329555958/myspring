package org.wch.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

@EnableBinding(Barista.class)
public class Consumer {

    @Autowired
    private Barista source;

    @StreamListener(Barista.OUTPUT_CHANNEL)
    public void receive(Message<String> msg) {
        System.out.println("receive:" + msg.getPayload());
    }
}
