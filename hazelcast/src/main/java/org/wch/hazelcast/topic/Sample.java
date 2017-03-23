package org.wch.hazelcast.topic;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.*;

public class Sample implements MessageListener<MyEvent> {

    public static void main(String[] args) {
        Sample sample = new Sample();
        ClientConfig config = new ClientConfig();
//        credentials config.getCredentials()
        ClientNetworkConfig networkConfig = config.getNetworkConfig();
        networkConfig.addAddress("10.65.213.21:5701");
        networkConfig.setConnectionAttemptLimit(5);
        networkConfig.setConnectionAttemptPeriod(10000);
        networkConfig.setConnectionTimeout(5000);
        HazelcastInstance instance = HazelcastClient.newHazelcastClient(config);
//        HazelcastInstance instance = Hazelcast.newHazelcastInstance();

        ITopic topic = instance.getTopic("wchtest");
        topic.addMessageListener(sample);
        MyEvent event = new MyEvent();
        event.setName("topictest");
        topic.publish(event);
        topic.publish(event);
        topic.publish(event);
        topic.publish(event);
        topic.publish(event);
        topic.publish(event);
//        System.out.println(topic.getLocalTopicStats().getReceiveOperationCount());
    }

    public void onMessage(Message<MyEvent> message) {
        MyEvent myEvent = message.getMessageObject();
        System.out.println("Message received = " + myEvent.getName());
    }

}