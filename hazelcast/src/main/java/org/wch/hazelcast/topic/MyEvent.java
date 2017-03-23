package org.wch.hazelcast.topic;

import java.io.Serializable;

/**
 * @author weichunhe
 *         Created on 2017/1/19.
 * @version 1.0
 */
public class MyEvent implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
