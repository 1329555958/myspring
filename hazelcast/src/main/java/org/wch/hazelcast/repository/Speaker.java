package org.wch.hazelcast.repository;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * @author weichunhe
 *         Created on 2016/9/19.
 * @version 1.0
 */
public class Speaker implements Serializable {
    @Id
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
