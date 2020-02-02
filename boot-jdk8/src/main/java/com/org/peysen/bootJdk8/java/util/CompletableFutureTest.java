package com.org.peysen.bootJdk8.java.util;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author : mengmeng.pei
 * @Date : 2019/12/23
 * @Desc :
 */
public class CompletableFutureTest {

    public static void main(String[] args) {
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1234;
        });

        if(integerCompletableFuture.isDone()){
            try {
                Integer integer = integerCompletableFuture.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
