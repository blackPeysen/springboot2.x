package com.org.peysen.bootcommon.thread.gcUtils;

import com.org.peysen.bootcommon.pojo.response.R;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author : mengmeng.pei
 * @Date : 2020/3/30
 * @Desc : visualVM分析死锁
 */
public class JvisualVMDemo {
    public static void main(String[] args) throws IOException {
        System.in.read();
        System.out.println("deadLock。。。");
        deadLock();
    }

    private static void deadLock(){
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();

        new Thread(()->{
            try {
                lock1.lock();
                Thread.sleep(100);
                lock2.lock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"MyThread1").start();

        new Thread(()->{
            try {
                lock2.lock();
                Thread.sleep(100);
                lock1.lock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"MyThread2").start();
    }
}
