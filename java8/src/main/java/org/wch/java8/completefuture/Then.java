package org.wch.java8.completefuture;

import org.wch.java8.CompletableFetureTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by weichunhe on 2016/6/30.
 */
public class Then {
    public static void main(String[] args) {
//        com();
//        eithor();
        of();
        System.out.println("endqq");
    }

    public static void callback() {
        CompletableFuture<Integer> future1 = CompletableFetureTest.getValue1();
        System.out.println(System.currentTimeMillis());
        future1.thenAccept(System.out::println);
        System.out.println(System.currentTimeMillis());
    }

    public static void complete() {

        CompletableFuture<Integer> future = new CompletableFuture<>();

        future.complete(1);

    }

    public static void of() {
        CompletableFuture<Integer> value1 = CompletableFetureTest.getValue1();
        CompletableFuture<Integer> value2 = CompletableFetureTest.getValue2();
        CompletableFuture<Integer> value3 = CompletableFetureTest.getValue3();
        CompletableFuture<Integer> value4 = CompletableFetureTest.getValue4();

//        CompletableFuture.anyOf(value4,value1, value2, value3).join();
        //到这里至少一个任务执行完成了
        List<CompletableFuture<Integer>> batch = new ArrayList<>();
        batch.add(value1);
        batch.add(value2);batch.add(value3);batch.add(value4);
        CompletableFuture.allOf(batch.toArray(new CompletableFuture[0])).join();
        System.out.println("end");
        //到这里至少所有任务都执行完成了
    }

    public static void eithor() {
        CompletableFuture<Integer> value1 = CompletableFetureTest.getValue1();
        CompletableFuture<Integer> value2 = CompletableFetureTest.getValue2();
        CompletableFuture<Integer> value3 = CompletableFetureTest.getValue3();
        CompletableFuture<Integer> value4 = CompletableFetureTest.getValue4();

        try {
            System.out.println("eithor:" + value2.applyToEither(value1, v -> v).get());
            System.out.println("eithor:" + value1.applyToEither(value2, v -> v).get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void com() {
        CompletableFuture<Integer> value1 = CompletableFetureTest.getValue1();
        CompletableFuture<Integer> value2 = CompletableFetureTest.getValue2();
        CompletableFuture<Integer> value3 = CompletableFetureTest.getValue3();
        CompletableFuture<Integer> value4 = CompletableFetureTest.getValue4();
        // 得到两个任务的执行结果，返回一个新的任务
        value1.thenCombine(value2, (x, y) -> {
            //x是value1的执行结果，y是value2的执行结果
            System.out.println(x + "," + y);
            return value3;
        });
        //得到结果返回一个新的任务
        value1.thenCompose(x -> {
            System.out.println(x);
            return value2;
        });
    }
}
