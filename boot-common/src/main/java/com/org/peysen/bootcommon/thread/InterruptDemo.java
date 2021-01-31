package com.org.peysen.bootcommon.thread;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: peimengmeng
 * @Date: 2021/1/31_09:10
 * @Desc:
 */
public class InterruptDemo {

    public static void main(String[] args) throws InterruptedException {
//        ThreadDemo threadDemo = new ThreadDemo();
//        threadDemo.start();
//
//        threadDemo.interrupt();

        ThreadDemo1 threadDemo1 = new ThreadDemo1();
        threadDemo1.start();

        TimeUnit.SECONDS.sleep(5);
        threadDemo1.interrupt();
    }


    static class ThreadDemo extends Thread{
        @Override
        public void run() {
            System.out.println("this is a demo thread");
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                /**
                 * 堵塞库中的方法，如sleep(),await()等方法在响应中断时：
                 *      1、清除当前线程的中断状态
                 *      2、抛出InterruptedException异常
                 */
                System.out.println("当前线程被中断，中断状态为：" + Thread.currentThread().isInterrupted());
            }
        }
    }

    /**
     * 校验在run中执行while循环是否会响应中断
     *
     */
    static class ThreadDemo1 extends Thread{
        @Override
        public void run() {
            try{
                while (!Thread.currentThread().isInterrupted()){
                    System.out.println("this is a demo thread");

                    TimeUnit.SECONDS.sleep(1);
                }
            }catch (InterruptedException e){
                System.out.println("当前线程被中断，中断状态为：" + Thread.currentThread().isInterrupted());
            }
        }
    }
}
