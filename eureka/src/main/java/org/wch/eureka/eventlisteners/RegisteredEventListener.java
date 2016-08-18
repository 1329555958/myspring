package org.wch.eureka.eventlisteners;

import com.netflix.appinfo.InstanceInfo;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.wch.util.JSONUtil;

/**
 * @author weichunhe
 *         Created on 2016/8/18.
 * @see com.netflix.eureka.registry.PeerAwareInstanceRegistryImpl#register(InstanceInfo, boolean)
 * 385 行去掉super 才能监听到reigstered事件
 */
@Component
public class RegisteredEventListener implements ApplicationListener<EurekaInstanceRegisteredEvent> {

    @Override
    public void onApplicationEvent(EurekaInstanceRegisteredEvent event) {
        System.out.println("registered:" + event.getInstanceInfo().getInstanceId() + ":" + event.getInstanceInfo().getAppName());
    }
}
