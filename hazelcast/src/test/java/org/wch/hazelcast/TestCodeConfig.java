package org.wch.hazelcast;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.junit.Assert;

/**
 * @author weichunhe
 *         Created on 2016/12/13.
 * @version 1.0
 */

public class TestCodeConfig {

    public static final String MapName = "learnTest";

    public static void main(String[] args) {
        ClientConfig config = new ClientConfig();
        GroupConfig groupConfig = config.getGroupConfig();
        groupConfig.setName("dev");
        groupConfig.setPassword("dev-pass");

        ClientNetworkConfig networkConfig = config.getNetworkConfig();
        networkConfig.addAddress("10.65.213.20:5701");
        networkConfig.setConnectionAttemptLimit(5);
        networkConfig.setConnectionAttemptPeriod(10000);
        networkConfig.setConnectionTimeout(5000);

        HazelcastInstance instance = HazelcastClient.newHazelcastClient(config);
        IMap map = instance.getMap(MapName);

        map.put("name", "learn");
        Assert.assertEquals("success", "learn", map.get("name"));
    }
}
