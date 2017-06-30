package com.wch.mq;

import java.io.Serializable;

/**
 * Created by weichunhe on 2016/5/19.
 */
public class Message implements Serializable {

    private String id, name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
