package org.wch.java8.completefuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author weichunhe
 *         Created on 2017/5/24.
 * @version 1.0
 */
public class FutureTest {
    static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        List<Future<Integer>> futures = new ArrayList<>();
        futures.add(executorService.submit(new Task(1)));
        futures.add(executorService.submit(new Task(10)));
        futures.add(executorService.submit(new Task(3)));
        futures.forEach((x)->{
            try {
                x.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        System.out.println("end");
    }
}

class Task implements Callable<Integer> {
    private int id;

    public Task(int id) {
        this.id = id;
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep(id * 1000);
        System.out.println(id);
        return id;
    }
}
