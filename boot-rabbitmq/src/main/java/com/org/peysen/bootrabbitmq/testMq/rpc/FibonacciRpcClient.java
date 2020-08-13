package com.org.peysen.bootrabbitmq.testMq.rpc;

/**
 * @Author Peysen
 * @Date 2020/8/13 21:47
 * @Desc TODO
 */
public class FibonacciRpcClient {

    public String call(String argu){
        return String.valueOf(fib(Integer.valueOf(argu)));
    }

    private static int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n-1) + fib(n-2);
    }
}
