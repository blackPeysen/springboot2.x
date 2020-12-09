package com.peysen.gof23.structural.bridge;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/8 19:44
 * @Desc:
 */
public class Client {
    public static void main(String[] args) {
        AbstractShape shape = new Circle(new RedCircle());
        shape.draw();
    }
}
