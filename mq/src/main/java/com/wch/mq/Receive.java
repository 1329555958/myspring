package com.wch.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.wch.util.JSONUtil;

/**
 * Created by weichunhe on 2016/5/19.
 */
@Component
public class Receive {
    private static Logger log = LoggerFactory.getLogger(Receive.class);
    private static int i = 0;

    /**
     * 消费过程中如果出现异常，就是没有消费成功，消息依然在队列中，还会再次消费
     * 消费完成后会给队列发个成功的消息，消息才会从队列中去掉
     *
     * @param content
     */
    @JmsListener(destination = Send.DESTINATION, concurrency = "10")
    public void processMessage(String content) {
        log.info("{}-receive:{}", System.currentTimeMillis(), content);
        try {
            Thread.currentThread().sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Message message = JSONUtil.fromJson(content, Message.class);
        log.info("receive-obj:id={},name={}", message.getId(), message.getName());
    }
}
