package com.org.peysen.bootJdk8.java.time.chrono;

import java.time.chrono.Chronology;
import java.util.Locale;

/**
 * @Author : mengmeng.pei
 * @Date : 2019/12/19
 * @Desc :
 */
public class AbstractChronologyTest {

    public static void main(String[] args) {
        Locale aDefault = Locale.getDefault();
        Chronology chronology = Chronology.ofLocale(aDefault);
        System.out.println(chronology.toString());


    }
}
