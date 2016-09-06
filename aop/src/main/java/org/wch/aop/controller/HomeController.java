package org.wch.aop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wch.aop.beans.BigCat;
import org.wch.aop.beans.Cat;

import javax.annotation.PostConstruct;

/**
 * @author weichunhe
 *         Created on 2016/9/5.
 * @version 1.0
 */
@RestController
public class HomeController {

    @Autowired
    private Cat cat;

    @PostConstruct
    public void init() {
        System.out.println(cat.getName());
        Cat cat = new Cat();
    }

    @RequestMapping("/hello")
    public Object hello() {
        return "hello";
    }

}
