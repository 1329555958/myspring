package org.wch.java8.segment;

/**
 * volatile 并不是同步机制，只保证可见性，并不保证原子性；不能进行指令重排优化
 * 1. 如果保证只有一个线程更新值，可以用来进行共享数据
 * 2. 与synchronized配合使用，synchronized用于对写加锁，读数据就可以共享了
 * 3. Volatile bean模式
 * 4. 一次性状态标识
 *
 * @author weichunhe
 *         Created on 2016/11/8.
 * @version 1.0
 */
public class VolatileTest {
    public static volatile int race = 0;

    public static void increase() {
        race++;
    }

    private static final int THREAD_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREAD_COUNT];

        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        increase();
                    }
                    System.out.println("end" + Thread.currentThread().getId());
                }
            });
            threads[i].start();
        }
        while (Thread.activeCount() > 1) {
//            System.out.println(Thread.activeCount());
            Thread.yield();
        }
        System.out.println(race);
    }
}
