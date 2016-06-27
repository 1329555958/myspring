package org.wch.stream;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;

@EnableBinding(Barista.class)
public class Consumer {

    //    @StreamListener(Barista.INPUT1)
//    @SendTo(Barista.OUTPUT1)
    @Transformer(inputChannel = Barista.INPUT1, outputChannel = Barista.OUTPUT1)
    public String receive(String msg) {
        System.out.println("StreamListener receive:" + msg);
        return msg + "-sendto";
    }

    @StreamListener(Barista.OUTPUT1)
    public void listener(String msg) {
        System.out.println("InboundChannelAdapter receive:" + msg);
    }

    @ServiceActivator(inputChannel = Barista.OUTPUT_CHANNEL, outputChannel = Barista.OUTPUT1)
    public String transform(String payload) {
        System.out.println("ServiceActivator transform:" + payload + "->" + payload.toUpperCase());
        return payload.toUpperCase();
    }

}
