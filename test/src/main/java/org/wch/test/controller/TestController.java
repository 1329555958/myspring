package org.wch.test.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wch.test.domain.User;
import org.wch.test.mock.Db;

/**
 * @author weichunhe
 *         Created on 2016/9/2.
 * @version 1.0
 */
@RestController
public class TestController {

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User register(String name, Integer age) {
        return Db.register(name, age);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public User update(@RequestBody User user) {
        return Db.register(user.getName(), user.getAge());
    }

    @RequestMapping("/query")
    public User query(String name) {
        return Db.query(name);
    }
}
