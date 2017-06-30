package com.wch.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.wch.util.JSONUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by weichunhe on 2016/5/19.
 * 1. 如果使用自定义类作为消息对象，因为安全问题需要配置
 * spring.activemq.packages.trusted=com.wch.mq
 * spring.activemq.packages.trust-all=true
 * 2. 默认是queue，如果需要使用topic，也需要配置 或者编码
 * spring.jms.pub-sub-domain=true
 * <p>
 * 3 . queue topic 二合一  virtual destination
 * 生产者发送到topic，消费者消费queue
 * jmsTemplate.setPubSubDomain(true);
 */
@Component
public class Receive {
    private static Logger log = LoggerFactory.getLogger(Receive.class);
    private static int i = 0;

    private JmsTemplate jmsTemplate;

    static final String DESTINATION = "mytopic";
    static final String VIRTUAL_TOPIC = "VirtualTopic.VT";

    static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        jmsTemplate.setPubSubDomain(true);
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * 消费过程中如果出现异常，就是没有消费成功，消息依然在队列中，还会再次消费
     * 消费完成后会给队列发个成功的消息，消息才会从队列中去掉
     *
     * @param
     */
    @JmsListener(destination = "Consumer.A." + VIRTUAL_TOPIC)
    public void processMessage(String message) {
        log.info("receive-processMessage:{}", message);
    }

    @JmsListener(destination = "Consumer.A." + VIRTUAL_TOPIC)
    public void processMessage2(String message) {
        log.info("receive-processMessage2:{}", message);
    }

    @JmsListener(destination = "Consumer.B." + VIRTUAL_TOPIC)
    public void processMessageB(String message) {
        log.info("receive-B:{}", message);
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void send() {
        Message message = new Message();
        message.setId(System.currentTimeMillis() + "");
        message.setName(DATE_FORMAT.format(new Date()));
        log.info("send,{}", JSONUtil.toJson(message));
        jmsTemplate.convertAndSend(VIRTUAL_TOPIC, message.getName());
    }

}
