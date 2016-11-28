package org.wch.java8.threads;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试把线程提交到线程池之后，执行的线程获取threadlocal变量
 *
 * @author weichunhe
 *         Created on 2016/11/9.
 * @version 1.0
 */
public class ThreadPoolTest {
    static ThreadLocal<String> name = new InheritableThreadLocal<>();

    static class A implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getId());
            System.out.println(name.get());
        }
    }

    public static void main(String[] args) {
        name.set(UUID.randomUUID().toString());
        System.out.println(name.get());
        ExecutorService service = Executors.newFixedThreadPool(3);
        service.submit(new A());
        service.submit(new A());
    }

}
