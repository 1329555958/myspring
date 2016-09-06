package org.wch.aop.beans;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author weichunhe
 *         Created on 2016/9/5.
 * @version 1.0
 */
@Component("cat")
public class Cat {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @PostConstruct
    public void init() {
        System.out.println("init cat");
    }

    public String name() {
        return "cat";
    }
}
