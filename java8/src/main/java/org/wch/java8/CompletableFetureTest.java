package org.wch.java8;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class CompletableFetureTest {

    private static long start;

    static ExecutorService executor = Executors.newFixedThreadPool(4);

    private static CompletableFuture<Integer> getValue1() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("a1");
                Thread.currentThread().sleep(1000);
                System.out.println("a12");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return 1;
        }, executor);
        return future;
    }

    private static CompletableFuture<Integer> getValue2() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("a2");
                Thread.currentThread().sleep(600);
                System.out.println("a22");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return 2;
        }, executor);
        return future;
    }

    private static CompletableFuture<Integer> getValue3() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("a3");
                Thread.currentThread().sleep(600);
                System.out.println("a33");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return 3;
        }, executor);
        return future;
    }

    private static CompletableFuture<Integer> getValue4() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("a4");
                Thread.currentThread().sleep(600);
                System.out.println("a44");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return 4;
        }, executor);
        return future;
    }

    public static Integer sum(Integer customer, Integer shop) {
        return customer + shop;
    }

    public static void main2(String[] args) throws Exception, ExecutionException {
        start = System.currentTimeMillis();

        CompletableFuture<Integer> value1 = getValue1();
        CompletableFuture<Integer> value2 = getValue2();
        CompletableFuture<Integer> value3 = getValue3();
        CompletableFuture<Integer> value4 = getValue4();

        // CompletableFuture<Integer> routeFuture = value1.thenCombine(value2,
        // (cust, shop) -> sum(cust, shop));
        // System.out.println(routeFuture.get());

        List<CompletableFuture<Integer>> list = new ArrayList<CompletableFuture<Integer>>();
        list.add(value1);
        list.add(value2);
        list.add(value3);
        list.add(value4);

        CompletableFuture<List<Integer>> result = allOf(list);

        List<Integer> resultList = result.get();

        for (int i = 0; i < resultList.size(); i++) {
            System.out.println(System.currentTimeMillis() + ":" + resultList.get(i));
        }
        System.out.println((System.currentTimeMillis() - start));

    }

    public static <T> CompletableFuture<List<T>> allOf(List<CompletableFuture<T>> futuresList) {
        CompletableFuture<Void> allFuturesResult = CompletableFuture
                .allOf(futuresList.toArray(new CompletableFuture[futuresList.size()]));
        return allFuturesResult
                .thenApply(v -> futuresList.stream().map(future -> future.join()).collect(Collectors.<T>toList()));
    }


    public static void main(String[] args) {

        CompletableFuture<Integer> value1 = getValue1();
        CompletableFuture<Integer> value2 = getValue2();
        CompletableFuture<Integer> value3 = getValue3();
        CompletableFuture<Integer> value4 = getValue4();
        //combine 将两个值组合在一起
        try {
            System.out.println("2+3=" + value2.thenCombine(value3, CompletableFetureTest::sum).get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //compose 将值组装成一个新的future
        try {
            System.out.println("compose:" + value1.thenCompose((Integer v) -> {
                return value4;
            }).get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        // anyof，allOf都是静态方法，allof执行之后还是要通过原来的引用获取结果
        try {
            System.out.println("anyof:" + CompletableFuture.anyOf(value4, value1, value2, value3).get());
            CompletableFuture.allOf(value1, value2);
            System.out.println("allof:" + value2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
