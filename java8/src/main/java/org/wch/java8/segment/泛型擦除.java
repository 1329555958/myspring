package org.wch.java8.segment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author weichunhe
 *         Created on 2016/11/8.
 * @version 1.0
 */
public class 泛型擦除 {

    public void list(List<String> names) {

    }

    /**
     * 由于泛型擦除的原因，这个方法与上面的方法是冲突的
     *
     * @param names
     */
//    public void list(List<Integer> names) {
//
//    }


    public static void main(String[] args) {
        泛型擦除 c = new 泛型擦除();
        List<String> names = Arrays.asList("1", "2");
        c.list(names);

    }
}
