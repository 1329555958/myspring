package org.wch.test.mock;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.util.Assert;
import org.wch.test.domain.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author weichunhe
 *         Created on 2016/9/2.
 * @version 1.0
 */
public class Db {
    private static Map<String, User> users = new HashedMap();

    public static User register(String name, int age) {
        Assert.notNull(name, "name is required!");
        Assert.isTrue(age > 0, "age must > 0");
        User u = new User();
        u.setName(name);
        u.setAge(age);
        users.put(name, u);
        return u;
    }

    public static User query(String name) {
        return users.get(name);
    }
}
