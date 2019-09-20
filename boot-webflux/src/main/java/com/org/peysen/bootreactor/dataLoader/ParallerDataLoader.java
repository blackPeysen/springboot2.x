package com.org.peysen.bootreactor.dataLoader;

import java.util.concurrent.*;

/**
 * @Description: 堵塞式数据加载
 * Created by mengmeng.Pei
 * 2019/8/28 18:02
 */
public class ParallerDataLoader extends DataLoader{

    protected void doLoad(){
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletionService completionService = new ExecutorCompletionService(executorService);

        completionService.submit(super::loadConfiguration,null);
        completionService.submit(super::loadOrders,null);
        completionService.submit(super::loadUsers,null);

        int count = 0;
        while(count < 3){
            if(completionService.poll() != null){
                count++;
            }
        }

        executorService.shutdown();

    }

    public static void main(String[] args) {
        new ParallerDataLoader().load();
    }

}
