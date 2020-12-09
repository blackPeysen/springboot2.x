package com.peysen.gof23.structural.bridge;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/8 19:44
 * @Desc:
 */
public class Circle extends AbstractShape {

    public Circle(IDrawAPI drawAPI) {
        super(drawAPI);
    }

    @Override
    public void draw() {
        drawAPI.drawCircle();
    }
}
