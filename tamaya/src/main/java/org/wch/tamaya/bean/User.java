package org.wch.tamaya.bean;

import org.apache.tamaya.inject.api.ConfigAutoInject;

import java.util.Date;

/**
 * @author weichunhe
 *         Created on 2016/11/28.
 * @version 1.0
 */
@ConfigAutoInject
public class User {
    private String name;
    private int age;
    private Date birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
