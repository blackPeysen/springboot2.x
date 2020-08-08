package com.org.peysen.bootcommon.baseConv;

/**
 * @Description: 进制转换
 * Created by mengmeng.Pei
 * 2019/9/20 11:07
 */
public class RadixMain {

    public static void main(String[] args) {
        Integer num = 112;
        //十进制转换为其他进制
        System.out.println("二进制：" + Integer.toBinaryString(num));
        System.out.println("八进制：" + Integer.toOctalString(num));
        System.out.println("十六进制：" + Integer.toHexString(num));

        //其他进制转换为十进制
        System.out.println("二进制-》十进制："+ Integer.parseInt("101100",2));
        System.out.println("八进制-》十进制："+ Integer.parseInt("23473",8));
        System.out.println("十六进制-》十进制："+ Integer.parseInt("EA8",16));
    }
}
