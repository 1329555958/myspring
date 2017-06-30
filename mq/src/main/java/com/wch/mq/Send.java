package com.wch.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wch.util.JSONUtil;

/**
 * Created by weichunhe on 2016/5/19.
 */
@RestController
@RequestMapping("/mq")
public class Send {
    private static Logger log = LoggerFactory.getLogger(Send.class);
    public static final String DESTINATION = "mqtest";
    private final JmsTemplate jmsTemplate;

    @Autowired
    public Send(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @RequestMapping("/send")
    public Object send() {
        Message message = new Message();
        message.setId(System.currentTimeMillis() + "");
        message.setName("activemq名称");

        jmsTemplate.convertAndSend(DESTINATION, message);

        return message;
    }
}
