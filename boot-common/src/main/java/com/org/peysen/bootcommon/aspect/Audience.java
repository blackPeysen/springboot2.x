package com.org.peysen.bootcommon.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @Description: 观众切面类，在表演前后进行一些独立操作
 * @Author: peysen
 * @CreateDate: 2019/7/22 07:49
 * @UpdateRemark: The modified content
 */

@Aspect
public class Audience {

    //定义切点
    @Pointcut("execution(* com.org.peysen.bootcommon.aspect.Performance.perform(..))")
    public void performance(){}


    //表演之前关闭手机
    @Before("performance()")
    public void silenceCellPhones(){
        System.out.println("silenceCellPhones");
    }

    //表演之前就坐
    @Before("performance()")
    public void takeSeats(){
        System.out.println("taking seats");
    }

    //表演之后鼓掌
    @AfterReturning("performance()")
    public void applause(){
        System.out.println("applause....");
    }

    //表演失败退票
    @AfterThrowing("performance()")
    public void demandRefund(){
        System.out.println("demandRefund....");
    }


    @Around("performance()")
    public void watchPerformance(ProceedingJoinPoint joinPoint){

        try{
            System.out.println("watchPerformance before。。。");
            joinPoint.proceed();

            System.out.println("watchPerformance after。。。");
        }   catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

}
