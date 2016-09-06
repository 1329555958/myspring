package org.wch.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author weichunhe
 *         Created on 2016/9/5.
 * @version 1.0
 */
//@Aspect
@Component
public class MyAspect {

    @AfterReturning("execution(* org.wch.aop.beans.Cat.*(..))")
    public void log(JoinPoint joinPoint) {
        System.out.println("Completed: " + joinPoint);
    }
}
