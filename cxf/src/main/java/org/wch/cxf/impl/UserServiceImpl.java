package org.wch.cxf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wch.cxf.bean.User;
import org.wch.cxf.service.UserService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author weichunhe
 *         Created on 2016/12/2.
 * @version 1.0
 */
public class UserServiceImpl implements UserService {
    static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private Map<Long, User> userMap = new HashMap<Long, User>();

    public UserServiceImpl() {
        User user = new User();
        user.setUserId(10001L);
        user.setUsername("liyd1");
        user.setEmail("liyd1@qq.com");
        user.setGmtCreate(new Date());
        userMap.put(user.getUserId(), user);
        user = new User();
        user.setUserId(10002L);
        user.setUsername("liyd2");
        user.setEmail("liyd2@qq.com");
        user.setGmtCreate(new Date());
        userMap.put(user.getUserId(), user);
        user = new User();
        user.setUserId(10003L);
        user.setUsername("liyd3");
        user.setEmail("liyd3@qq.com");
        user.setGmtCreate(new Date());
        userMap.put(user.getUserId(), user);
    }

    @Override
    public String getName(Long userId) {
        return "liyd-" + userId;
    }

    @Override
    public User getUser(Long userId) {

        log.info("获取用户,userId = {}", userId);
        return userMap.get(userId);
    }
}
