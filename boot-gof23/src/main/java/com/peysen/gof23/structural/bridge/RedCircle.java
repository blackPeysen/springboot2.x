package com.peysen.gof23.structural.bridge;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/8 19:41
 * @Desc:
 */
public class RedCircle implements IDrawAPI {
    @Override
    public void drawCircle() {
        System.out.println("red Circle ....");
    }
}
