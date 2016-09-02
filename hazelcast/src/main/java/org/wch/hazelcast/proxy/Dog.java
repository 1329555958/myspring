package org.wch.hazelcast.proxy;

/**
 * @author weichunhe
 *         Created on 2016/9/2.
 * @version 1.0
 */
public class Dog implements Animal {
    @Override
    public String name() {
        return "dog";
    }

    public final String test() {
        return "i am parent!";
    }
}
