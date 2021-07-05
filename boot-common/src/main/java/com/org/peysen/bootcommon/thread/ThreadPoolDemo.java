package com.org.peysen.bootcommon.thread;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: peimengmeng
 * @Date: 2021/2/1_07:42
 * @Desc: 验证设置线程池的核心数为0时，有界队列为5时，添加任务时底层线程池如何调度
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 5, 0, TimeUnit.MICROSECONDS,
                new LinkedBlockingDeque<>(5));

        for (int i = 0; i < 10; i++) {
            int currenty = i;
            threadPoolExecutor.submit( ()-> {
                try {
                    System.out.println("ThreadName:" + Thread.currentThread().getName() + ": " + currenty);
                    Thread.currentThread().sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        System.out.println(threadPoolExecutor.getLargestPoolSize());


    }
}
