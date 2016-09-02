package org.wch.util;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * @author weichunhe
 *         Created on 2016/9/2.
 * @version 1.0
 */

public class TestRestUtil {
    private Map<String, String> param;

    @Before
    public void before() {
        param = new HashedMap();
        param.put("name", "vf");
        param.put("age", "10");
    }

    @Test
    public void testMakeGetUrl() {

        String search = RestUtil.makeGetUrl(param, true);
        Assert.isTrue(search.startsWith("?"));
        search = RestUtil.makeGetUrl(param, false);
        Assert.isTrue(search.startsWith("&"));
        search = RestUtil.makeGetUrl(param, true, "name");

        Assert.isTrue("?age={age}".equals(search));
    }

    @Test
    public void testMakePostJSONEntiry() {
        HttpEntity<String> entity = RestUtil.makePostJSONEntiry(param);
        Assert.isTrue(entity.getHeaders().getContentType().equals(MediaType.APPLICATION_JSON_UTF8));
    }
}
