package org.wch.hazelcast.config;

import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.config.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author weichunhe
 *         Created on 2016/9/1.
 * @version 1.0
 */
@Configuration
public class HazelcastConfiguration {
    @Bean
    public ClientConfig config() {
        ClientConfig config = new ClientConfig();
        ClientNetworkConfig networkConfig = config.getNetworkConfig();
        networkConfig.addAddress("10.5.6.34:5701");
        return config;
//        Config config = new Config();
//        ManagementCenterConfig managementCenterConfig = new ManagementCenterConfig();
//        managementCenterConfig.setEnabled(true);
//        managementCenterConfig.setUrl("http://localhost:9090/mancenter");
//        config.setManagementCenterConfig(managementCenterConfig);

//        clientconfig
//        return config
//                .setProperty("hazelcast.logging.type", "slf4j");
//        new Config().addMapConfig(
//                new MapConfig()
//                        .setName("accepted-messages")
//                        .setEvictionPolicy(EvictionPolicy.LRU)
//                        .setTimeToLiveSeconds(2400))
    }
}
