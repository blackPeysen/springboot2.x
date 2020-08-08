package com.org.peysen.bootcommon.thread;

import java.util.concurrent.*;

/**
 * @Author : mengmeng.pei
 * @Date : 2020/3/4
 * @Desc :
 */
public class DivTask implements  Runnable {
    int a, b;

    public DivTask(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        double re =a/b;
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "-" +thread.getState() + "-" + thread.getThreadGroup());
        System.out.println("result: " + re);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 10, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

//        for(int i=0;i<5;i++){
//            // 使用submit不会出现异常堆栈信息，因为submit返回一个Future对象,并没有get
//            threadPoolExecutor.submit(new DivTask(100, i));
//            // 使用execute会出现异常堆栈信息
//            threadPoolExecutor.execute(new DivTask(100, i));
//            // 使用submit-get方式会出现异常堆栈信息
//            threadPoolExecutor.submit(new DivTask(100, i)).get();
//        }

        TraceThreadPoolExecutor poolExecutor = new TraceThreadPoolExecutor(0, 10,
                0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        for(int i=0;i<5;i++){
//            poolExecutor.execute(new DivTask(100, i));

            poolExecutor.submit(new DivTask(100,i)).get();
        }
    }

    static class TraceThreadPoolExecutor extends ThreadPoolExecutor{
        public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        @Override
        public Future<?> submit(Runnable task) {
            return super.submit(wrap(task, clientTrace()));
        }

        @Override
        public void execute(Runnable command) {
            super.execute(wrap(command, clientTrace()));
        }

        private Exception clientTrace(){
            return new Exception("Client stask trace.");
        }

        private Runnable wrap(final Runnable task, final Exception clientStask){
            return new Runnable() {
                @Override
                public void run() {
                    try {
                        task.run();
                    }catch (Exception e){
                        clientStask.printStackTrace();
                        throw e;
                    }
                }
            };
        }
    }
}
