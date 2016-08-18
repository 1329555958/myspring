package org.wch.eureka.eventlisteners;

import org.springframework.cloud.netflix.eureka.server.event.EurekaServerStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.wch.util.JSONUtil;

/**
 * @author weichunhe
 *         Created on 2016/8/18.
 */
@Component
public class ServerStartedEventListener implements ApplicationListener<EurekaServerStartedEvent> {
    @Override
    public void onApplicationEvent(EurekaServerStartedEvent event) {
        System.out.println("started:" + event.getTimestamp());
    }
}
