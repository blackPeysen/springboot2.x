package com.org.peysen.bootcommon.thread;

import javax.script.ScriptException;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @Author : mengmeng.pei
 * @Date : 2020/3/6
 * @Desc :
 */
public class ThreadLocalDemo {
    public static final int GEN_COUNT =10000000;
    public static final int THREAD_COUNT = 4;
    public static ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
    public static Random random = new Random(123);


    static class RndTask implements Callable<Long>{
        public static ThreadLocal<Random> threadLocal = new ThreadLocal<Random>(){
            @Override
            protected Random initialValue() {
                return new Random(123);
            }
        };
        private int mode = 0;

        public RndTask(int mode) {
            this.mode = mode;
        }

        public Random getRandom(){
            if (mode == 0){
                return random;
            }else if(mode == 1){
                return threadLocal.get();
            }else{
                return null;
            }
        }

        @Override
        public Long call() throws Exception {
            long b = System.currentTimeMillis();
            for(int i=0;i<GEN_COUNT;i++){
                getRandom().nextInt();
            }
            long e = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + "Spend" + (e-b) +"ms");

            return e-b;
        }
    }






    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<Long>[]  futures = new Future[THREAD_COUNT];

        for(int i =0 ;i<THREAD_COUNT;i++){
            futures[i] = executorService.submit(new RndTask(0));
        }
        long timeTotal =0 ;
        for(int i =0 ;i<THREAD_COUNT;i++){
            timeTotal += futures[i].get();
        }
        System.out.println("多线程访问同一个Random实例，共花费：" + timeTotal + "ms.");

        for(int i =0 ;i<THREAD_COUNT;i++){
            futures[i] = executorService.submit(new RndTask(1));
        }
        timeTotal =0 ;
        for(int i =0 ;i<THREAD_COUNT;i++){
            timeTotal += futures[i].get();
        }
        System.out.println("使用ThreadLocal保证Random实例，共花费：" + timeTotal + "ms.");

        executorService.shutdown();
    }
}
