package org.wch.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Barista.class)
public class Producer {

    @Autowired
    private Barista source;

    public void producer(String msg) {
        source.logOutPut().send(MessageBuilder.withPayload(msg).build());
        System.out.println("send:" + msg);
    }

}
