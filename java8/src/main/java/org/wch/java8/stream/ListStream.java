package org.wch.java8.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @author weichunhe
 *         Created on 2016/11/29.
 * @version 1.0
 */
public class ListStream {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("a", "b", "_", "-");
        System.out.println(words.parallelStream().filter(x -> {
            return x.length() == 1;
        }).count());

        //zz
    }
}
