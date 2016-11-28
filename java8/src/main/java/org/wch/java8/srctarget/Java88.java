package org.wch.java8.srctarget;

import java.util.Objects;

/**
 * @author weichunhe
 *         Created on 2016/11/22.
 * @version 1.0
 */
public class Java88 {
    public static void main(String[] args) {
        Objects.requireNonNull(null, () -> {
            return "i am java6 class";
        });
    }
}
