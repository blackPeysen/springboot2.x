package com.org.peysen.bootcommon.functionals.function;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.function.ToDoubleFunction;

/**
 * @Author:peimengmeng
 * @Date: 2020/12/27 14:46
 * @Desc: 函数接口BinaryOperator 的测试代码
 */
public class BinaryOperatorClient {
    public static void main(String[] args) {
        for (double i = 0; i < 10; i++) {
            BinaryOperator<Double> func2 = (x, y) -> x + y;
            System.out.println(func2.apply(i, i+1));

            ToDoubleFunction<Double> toDouble = a -> Double.valueOf(a * 2);
            BinaryOperator<Double> tBinaryOperator = BinaryOperator.minBy(Comparator.comparingDouble(toDouble));
            Double apply = tBinaryOperator.apply(i, i + 1);
            System.out.println("compator = " + apply);
        }
    }

}
