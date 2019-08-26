package com.org.peysen.bootayncmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * @author : peysen
 * @Desc:
 * @date :
 */

@RestController
@EnableScheduling
public class AyncController {
    private final static Logger logger = LoggerFactory.getLogger(AyncController.class);
    private final BlockingQueue<DeferredResult<String>> blockingDeque = new ArrayBlockingQueue<>(5);
    private final Random random = new Random();

    @Scheduled(fixedRate = 5000)
    private void process() throws InterruptedException {
        DeferredResult<String> result = null;
        do {
            result = blockingDeque.take();
            //随机超时时间
            long timeOut = random.nextInt(100);

            //模拟等待时间，RPC或者DB查询
            Thread.sleep(timeOut);

            result.setResult("helloWorld");
            printf("执行计算结果，消耗：" + timeOut);

        }while (result != null);
    }

    @GetMapping("/helloWorld")
    public DeferredResult<String> helloWorld(){
        DeferredResult<String> result = new DeferredResult<>(50L);

        //入队操作
        blockingDeque.offer(result);

        result.onCompletion(()->{
            printf("执行成功\r\n");
        });

        result.onTimeout(()->{
            printf("执行超时\n\r");
        });

        return result;
    }


    private void printf(Object object){
        logger.info("AyncController {} exec {}.", Thread.currentThread().getName(), object);

    }
}
