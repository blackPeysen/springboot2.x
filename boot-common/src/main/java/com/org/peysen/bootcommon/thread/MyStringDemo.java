package com.org.peysen.bootcommon.thread;

import java.util.concurrent.CountDownLatch;

public class MyStringDemo {
    private String string;	
	
    private String getString() {	
        return string;	
    }	
	
    private void setString(String string) {	
        this.string = string;	
    }	
	
    public static void main(String[] args) {	
        int threads = 9;	
        MyStringDemo demo = new MyStringDemo();	
        CountDownLatch countDownLatch = new CountDownLatch(threads);
        for (int i = 0; i < threads; i++) {
//            System.out.println("i=" + i);
            Thread thread = new Thread(() -> {
                /**
                 * 在i-0线程执行的同时，i-1线程也在执行，
                 * 此时i-0线程还没执行println，
                 * 而i-1此时demo.set()将i-0线程的i置为了1
                 */
                demo.setString(Thread.currentThread().getName());
                System.out.println(demo.getString());	
                countDownLatch.countDown();	
            }, "thread - " + i);	
            thread.start();	
        }	
    }
	
}