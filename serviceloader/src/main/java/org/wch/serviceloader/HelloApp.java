package org.wch.serviceloader;

import java.util.ServiceLoader;

/**
 * @author weichunhe
 *         Created on 2016/11/28.
 * @version 1.0
 */
public class HelloApp {
    public static void main(String[] args) {
        ServiceLoader<IHello> anymals = ServiceLoader.load(IHello.class);
        anymals.forEach(x -> {
            x.hello();
        });
    }
}
