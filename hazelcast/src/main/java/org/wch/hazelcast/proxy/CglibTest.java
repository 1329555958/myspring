package org.wch.hazelcast.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author weichunhe
 *         Created on 2016/9/2.
 * @version 1.0
 */
public class CglibTest {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Dog.class);
        enhancer.setCallback(new Cb());
        Animal dog = (Animal) enhancer.create();
        System.out.println(dog.name());
    }
}

class Cb implements MethodInterceptor{

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib final proxy");
        return methodProxy.invokeSuper(o, objects);
    }

}
