package com.peysen.gof23.structural.facade;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/10 9:16
 * @Desc:
 */
public class DVDPlay {
    private static final DVDPlay instance = new DVDPlay();

    public static DVDPlay getInstance() {
        return instance;
    }

    public void open(){
        System.out.println("打开dvd");
    }

    public void close(){
        System.out.println("关闭dvd");
    }
}
