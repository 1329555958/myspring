package org.wch.stream.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Source.class)
public class Producer {
    Logger log = LoggerFactory.getLogger(Producer.class);
    int count = 0;
    @Autowired
    private Source source;


    public void producer(String msg) {
        msg = msg + ++count;
        log.info("producer:{},{}", msg, source.output().send(MessageBuilder.withPayload(msg).build()));
    }


    public String send(String msg) {
        msg = msg + ++count;
        log.info("send:{},{}", msg, source.output().send(MessageBuilder.withPayload(msg).build()));
//        channel.send(MessageBuilder.withPayload(msg).build());
        return msg;
    }

}
