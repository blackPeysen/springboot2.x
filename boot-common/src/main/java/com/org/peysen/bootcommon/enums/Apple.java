package com.org.peysen.bootcommon.enums;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author:peimengmeng
 * @Date: 2020/12/17 21:03
 * @Desc: 单纯的定义枚举时，枚举的值对应的是int值，从0开始
 */
public enum Apple {
    FUJI,
    PIPPIN,
    GRANNY_SMITH,
    ;

    private static  final Map<Integer, Apple> appleMap = Stream.of(values()).collect(Collectors.toMap(Enum::ordinal, Function.identity()));

    public static void main(String[] args) {
        Apple apple1 = Apple.valueOf("FUJI");
        System.out.println(apple1.ordinal());

        for (Apple apple : Apple.values()){
            System.out.println(apple.ordinal());
        }

        System.out.println(appleMap.get(0));
    }
}
