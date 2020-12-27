package com.org.peysen.bootcommon.functionals.function;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

/**
 * @Author:peimengmeng
 * @Date: 2020/12/27 14:46
 * @Desc: 函数接口BiFunction 的测试代码
 */
public class BiFunctionClient {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        for (double i = 0; i < 10; i++) {
            int result = test(i, i+1, (a, b) -> Double.valueOf(Math.pow(a, b)).intValue());

            System.out.println("result=" + result);
        }
    }

    private static int test(Double t, Double u, BiFunction<Double, Double, Integer> biFunction){
        return biFunction.apply(t,u);
    }
}
