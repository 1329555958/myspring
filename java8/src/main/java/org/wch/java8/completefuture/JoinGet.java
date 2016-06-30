package org.wch.java8.completefuture;

import javassist.bytecode.CodeAttribute;

import java.util.concurrent.CompletableFuture;

/**
 * Created by weichunhe on 2016/6/30.
 */
public class JoinGet {

    public static void main(String[] args) {
        CompletableFuture<Integer> join = new CompletableFuture<>();
        Delay.execute(2000, join, 10, 20);
        System.out.println("before");
        System.out.println(join.join());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after");
        System.out.println(join.join());
    }
}

