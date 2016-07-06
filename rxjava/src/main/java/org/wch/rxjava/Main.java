package org.wch.rxjava;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by weichunhe on 2016/7/6.
 */
public class Main {

    public static void helloWorld() {
        Observable.just("hello world").subscribe(System.out::println);
    }

    public static void subscriber() {
        // 创建observable
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello RxJava");
                subscriber.onCompleted();
            }
        });

// 创建subscriber
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        };

// 订阅
        System.out.println(System.currentTimeMillis());
        observable.map(s -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s + "map";
        }).subscribe(subscriber);
        System.out.println(System.currentTimeMillis());
    }

    public static void from() {
//        Observable.from()
    }

    public static void main(String[] args) {
//        helloWorld();
        //有啥用？只能观察一次？
        subscriber();
    }
}
