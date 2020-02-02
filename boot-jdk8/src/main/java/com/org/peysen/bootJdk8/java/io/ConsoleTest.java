package com.org.peysen.bootJdk8.java.io;

import java.io.Console;

/**
 * @Author : mengmeng.pei
 * @Date : 2019/12/18
 * @Desc :
 */
public class ConsoleTest {

    public static void main(String[] args) {
        Console console = System.console();
        String s = console.readLine();
        System.out.println(s);
    }
}
