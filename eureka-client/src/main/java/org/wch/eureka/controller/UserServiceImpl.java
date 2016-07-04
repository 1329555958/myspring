package org.wch.eureka.controller;

import org.springframework.stereotype.Service;

/**
 * Created by weichunhe on 2016/5/23.
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * 查找用户
     *
     * @param id
     * @param name
     * @return
     */
    @Override
    public String findUserByIdAndName(String id, String name) {
        return String.format("id=%s,name=%s", id, name);
    }
}
