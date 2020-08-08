package com.org.peysen.bootcommon.thread;

import com.org.peysen.bootcommon.pojo.response.R;
import org.apache.poi.ss.formula.functions.T;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Author : mengmeng.pei
 * @Date : 2020/3/6
 * @Desc : 并行计算demo
 */
public class ParallelThreadDemo {

    public static void main(String[] args) {
        new Thread(new Plus()).start();
        new Thread(new Multiply()).start();
        new Thread(new Div()).start();

        for(int i=0; i<1000; i++){
            for(int j =0 ;j<1000; j++){
                Msg msg = new Msg(i,j, "(("+ i + "+"+j+")*" + i + ")/2");
                Plus.blockingQueue.add(msg);
            }
        }
    }


    // 定义装载数据的载体
    static class Msg{
        public int i;
        public int j;
        public String orgStr;

        public Msg(int i, int j, String orgStr) {
            this.i = i;
            this.j = j;
            this.orgStr = orgStr;
        }
    }

    //加法
    static class Plus implements Runnable {
        public static BlockingQueue<Msg> blockingQueue = new LinkedBlockingDeque<>();

        @Override
        public void run() {
            while(true){
                try {
                    Msg msg = blockingQueue.take();
                    msg.j = msg.i + msg.j;
                    Multiply.blockingQueue.add(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //乘法
    static class Multiply implements Runnable {
        public static BlockingQueue<Msg> blockingQueue = new LinkedBlockingDeque<>();

        @Override
        public void run() {
            while(true){
                try {
                    Msg msg = blockingQueue.take();
                    msg.i = msg.i * msg.j;
                    Div.blockingQueue.add(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    //乘法
    static class Div implements Runnable {
        public static BlockingQueue<Msg> blockingQueue = new LinkedBlockingDeque<>();

        @Override
        public void run() {
            while(true){
                try {
                    Msg msg = blockingQueue.take();
                    msg.i = msg.i / 2;
                    System.out.println(msg.orgStr + " = " + msg.i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
