package org.wch.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface Barista {

    String INPUT_CHANNEL = "input_channel";
    String OUTPUT_CHANNEL = "output_channel";

    String INPUT1 = "input1";
    String OUTPUT1 = "output1";

    @Input(Barista.INPUT_CHANNEL)
    SubscribableChannel logInput();

    @Output(Barista.OUTPUT_CHANNEL)
    MessageChannel logOutPut();

    @Input(Barista.INPUT1)
    SubscribableChannel input1();

    @Output(Barista.OUTPUT1)
    MessageChannel output1();
}