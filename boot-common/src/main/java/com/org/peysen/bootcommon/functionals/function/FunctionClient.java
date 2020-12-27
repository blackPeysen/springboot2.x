package com.org.peysen.bootcommon.functionals.function;

import java.util.function.Function;

/**
 * @Author:peimengmeng
 * @Date: 2020/12/27 15:37
 * @Desc: 测试Function 函数接口
 *  Function功能： 输入一个T类型，返回一个R类型，常用于数据转换
 */
public class FunctionClient {
    public static void main(String[] args) {
        /**
         * 输入一个Double类型，返回一个Integer类型
         */
        Function<Double, Integer> function = a -> Double.valueOf(a).intValue();

        // 形成组合函数，先将double * 2，再转为int
        Function<Double, Double> before = a -> a * 2;
        function = function.compose(before);

        for (double d = 0; d<10d; d++){
            Integer integer = function.apply(d);
            System.out.println("result = " + integer);
        }

        System.out.println();

        /**
         * Function.identity(): 用于返回自身
         */
        Function<Integer, Integer> identity = Function.identity();
        for (int i = 0; i < 10; i++) {
            System.out.println("result = " + identity.apply(i));
        }
    }
}
