package org.wch.hazelcast.topic;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;
import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Sample implements MessageListener<MyEvent> {

    public static void main(String[] args) {
        Sample sample = new Sample();
        ClientConfig config = new ClientConfig();
//        config.getCredentials().
        ClientNetworkConfig networkConfig = config.getNetworkConfig();
        networkConfig.addAddress("10.65.215.11:5701");
        networkConfig.setConnectionAttemptLimit(5);
        networkConfig.setConnectionAttemptPeriod(10000);
        networkConfig.setConnectionTimeout(5000);
//        config.getGroupConfig().setName("cluster10.5.77.11-15").setPassword("105771115-pass");
        HazelcastInstance instance = HazelcastClient.newHazelcastClient(config);
        ITopic topic = instance.getTopic("wchtest");
        topic.addMessageListener(sample);
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss.sss");
        MyEvent event = new MyEvent();
        topic.publish(event);
//        while (true){
//            event.setName(df.format(new Date()));
//            topic.publish(event);
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

//        System.out.println(topic.getLocalTopicStats().getReceiveOperationCount());
    }

    public void onMessage(Message<MyEvent> message) {
        MyEvent myEvent = message.getMessageObject();
        System.out.println("Message received = " + myEvent.getName());
    }

}