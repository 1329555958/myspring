package org.wch.test;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import org.wch.test.domain.User;
import org.wch.util.RestUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author weichunhe
 *         Created on 2016/9/2.
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(TestApp.class)
@WebIntegrationTest("server.port:0") //server.port:0
public class TestCase {

    private RestTemplate rest = new RestTemplate();

    @Value("http://localhost:${local.server.port}")
    private String baseUrl;

    @Test
    public void testRegister() {
        Map<String, String> param = new HashedMap();
        param.put("name", "test");
        param.put("age", "20");
        User u = RestUtil.postText(rest, baseUrl + "/register", param, User.class);
        Assert.isTrue(u.getName().equals(param.get("name")));
    }

    @Test
    public void testUpdate() {
        Map<String, String> param = new HashedMap();
        param.put("name", "test");
        param.put("age", "20");
        User u = RestUtil.postJSON(rest, baseUrl + "/update", param, User.class);
        Assert.isTrue(u.getName().equals(param.get("name")));
        User query = rest.getForObject(baseUrl + "/query" + RestUtil.makeGetUrl(param, true, "age"), User.class, param);
        Assert.isTrue(query.getAge() == Integer.valueOf(param.get("age")));
    }


}
