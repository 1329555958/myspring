package org.wch.java8.segment;

/**
 * 静态分派
 * 重载属于静态分派，重写属于动态分派
 *
 * @author weichunhe
 *         Created on 2016/11/8.
 * @version 1.0
 */
public class StaticDispatch {
    static class Human {

    }

    static class Man extends Human {

    }

    static class Women extends Human {

    }

    static void hello(Human human) {
        System.out.println("human");
    }

    static void hello(Man human) {
        System.out.println("man");
    }

    static void hello(Women human) {
        System.out.println("woman");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Women woman = new Women();
        //重载是静态分派，根据静态类型进行分派，静态类型就是定义时的类型，动态类型就是实际类型
        hello(man);
        hello(woman);
    }
}
