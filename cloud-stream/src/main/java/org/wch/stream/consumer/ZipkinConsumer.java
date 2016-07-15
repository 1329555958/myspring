package org.wch.stream.consumer;

import org.springframework.cloud.sleuth.stream.SleuthSink;
import org.springframework.cloud.sleuth.stream.Spans;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.wch.util.JSONUtil;

/**
 * Created by weichunhe on 2016/7/15.
 */
@EnableBinding(SleuthSink.class)
@MessageEndpoint
public class ZipkinConsumer {

    @ServiceActivator(inputChannel = SleuthSink.INPUT)
    public void sink(String input) throws Exception {
        System.out.println("receive spans");
        System.out.println(input);
    }
}
