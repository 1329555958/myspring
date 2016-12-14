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
public class TestXMLConfig {

    public static void main(String[] args) {

        HazelcastInstance instance = HazelcastClient.newHazelcastClient();
        IMap map = instance.getMap(TestCodeConfig.MapName);
        map.put("xmlTest", "learn");
        System.out.println(map.get("xmlTest"));
    }
}
