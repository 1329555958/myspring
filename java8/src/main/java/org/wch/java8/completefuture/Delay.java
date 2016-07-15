package org.wch.java8.completefuture;


import java.util.concurrent.CompletableFuture;

/**
 * Created by weichunhe on 2016/6/30.
 */
public class Delay<T> implements Runnable {
    private long delay = 0;
    private CompletableFuture<T> future;
    private T result;
    private T obtrude;

    public Delay(long delayMs, CompletableFuture<T> future, T result, T obtrude) {
        delay = delayMs;
        this.future = future;
        this.result = result;
        this.obtrude = obtrude;
    }

    public static <T> void execute(long delayMs, CompletableFuture<T> future, T result, T obtrude) {
        new Thread(new Delay<T>(delayMs, future, result, obtrude)).start();
    }

    @Override
    public void run() {
        future.exceptionally(ex -> result);
        try {
            Thread.sleep(delay);
            future.complete(result);
            Thread.sleep(delay);
            future.obtrudeValue(obtrude);
            future.obtrudeException(new RuntimeException("has completed"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
