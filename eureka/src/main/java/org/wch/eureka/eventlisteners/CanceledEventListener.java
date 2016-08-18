package org.wch.eureka.eventlisteners;

import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.wch.util.JSONUtil;

/**
 * @author weichunhe
 *         Created on 2016/8/18.
 */
@Component
public class CanceledEventListener implements ApplicationListener<EurekaInstanceCanceledEvent> {

    @Override
    public void onApplicationEvent(EurekaInstanceCanceledEvent event) {
        System.out.println("canceled:" + event.getServerId());
    }
}
