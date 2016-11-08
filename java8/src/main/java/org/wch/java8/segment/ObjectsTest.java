package org.wch.java8.segment;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author weichunhe
 *         Created on 2016/11/3.
 * @version 1.0
 */
public class ObjectsTest {
    public static void main(String[] args) {
        System.out.println(Logger.getGlobal().getLevel());
        Logger.getGlobal().info(() -> {
            return "sdfadfh";
        });
    }
}
