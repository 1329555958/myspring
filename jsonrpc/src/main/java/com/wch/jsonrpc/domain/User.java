package com.wch.jsonrpc.domain;

/**
 * Created by weichunhe on 2016/5/23.
 */
public class User {
    private int age;
    private String name;
    private String id;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String toString() {
        return String.format("id=%s,name=%s,age=%s", id, name, age);
    }

}
