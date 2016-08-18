package org.wch.eureka.eventlisteners;

import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author weichunhe
 *         Created on 2016/8/18.
 *         客户端需要发送renew事件到服务端，每30s
 */
@Component
public class RenewedEventListener implements ApplicationListener<EurekaInstanceRenewedEvent> {

    @Override
    public void onApplicationEvent(EurekaInstanceRenewedEvent event) {
        System.out.println("renewed:" + event.getServerId());
    }
}
