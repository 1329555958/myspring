package org.wch.fragment.classload;

/**
 * @author weichunhe
 *         Created on 2016/10/27.
 * @version 1.0
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("main");

        Hello hello = null;
        if (args.length != 0) {
            if (args[0].equals("1")) {
                System.out.println("hello new");
                hello = new Hello();
                hello.hello();
            } else if (args[0].equals("2")) {
                System.out.println("hello forname no init");
                try {
                    Class hc = Class.forName("org.wch.fragment.classload.Hello", false, Test.class.getClassLoader());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (args[0].equals("3")) {
                System.out.println("hello forname init ");
                try {
                    Class hc = Class.forName("org.wch.fragment.classload.Hello");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (args[0].equals("4")) {
                System.out.println("hello forname new  ");
                try {
                    Class hc = Class.forName("org.wch.fragment.classload.Hello");
                    hc.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (args[0].equals("5")) {
                System.out.println("hello obj 2 hello");
                try {
                    Object o = "";
                    hello = (Hello) o;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        System.out.println("hello null");
    }
}
