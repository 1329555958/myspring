package org.wch.hazelcast;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author weichunhe
 *         Created on 2016/9/2.
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(HazelcastApp.class)
@WebIntegrationTest("server.port:2222")
public class TestPut {

    private RestTemplate template = new RestTemplate();

    private String value = null;

    @Before
    public void before() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append(i);
        }
        value = sb.toString();
        System.out.println(value);
    }

    @Test
    public void put() {
        Map<String, String> param = new HashedMap();
        String url = "http://localhost:2222/set?key={key}&value={value}";
        String result = null;
        int step = 10000;
        int start = 0 * step;
        for (int i = start; i < start + step; i++) {
            param.put("key", "k" + i);
            param.put("value", i + "-" + value);
            result = template.getForObject(url, String.class, param);
            Assert.assertEquals("put error", result, "success");
        }

    }

    public void get() {
        Map<String, String> param = new HashedMap();
        String url = "http://localhost:2222/set?key={key}&value={value}";
        String result = null;

    }
}
