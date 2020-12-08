package com.peysen.gof23.creational.builder;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/8 10:37
 * @Desc:
 */
public class client {
    public static void main(String[] args) {
        Computer computer = new ComputerBuilber("因特尔", "三星")
                                        .buildDisplay("三星24寸").buildkeyboard("罗技").buildUsbCount(2)
                                        .builder();

        System.out.println(computer.toString());
    }
}
