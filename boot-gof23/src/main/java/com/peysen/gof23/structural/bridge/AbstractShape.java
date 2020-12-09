package com.peysen.gof23.structural.bridge;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/8 19:39
 * @Desc:
 */
public abstract class AbstractShape {
    protected IDrawAPI drawAPI;

    public AbstractShape(IDrawAPI drawAPI) {
        this.drawAPI = drawAPI;
    }

    protected abstract void draw();

}
