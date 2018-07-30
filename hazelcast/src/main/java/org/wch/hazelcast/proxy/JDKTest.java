package org.wch.hazelcast.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author weichunhe
 *         Created on 2016/9/2.
 * @version 1.0
 */
public class JDKTest {
    public static void main(String[] args) {
        Handler h = new Handler();
        Animal cat = (Animal) Proxy.newProxyInstance(Handler.class.getClassLoader(), new Class[]{Animal.class,Pet.class}, h);
        System.out.println(cat.name());
        System.out.println(((Pet) cat).petName());
    }
}

class Handler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk22 proxy : "+method.getName());
        return "cat string"; //method.invoke(proxy, args);
    }
}
