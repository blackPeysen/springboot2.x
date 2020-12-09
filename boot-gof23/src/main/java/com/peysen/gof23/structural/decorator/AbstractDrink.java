package com.peysen.gof23.structural.decorator;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/9 11:02
 * @Desc:
 */
public abstract class AbstractDrink implements ICalculatePrice{

    protected String drinkName;

    protected double drinkPrice;

    public AbstractDrink(String drinkName, double drinkPrice) {
        this.drinkName = drinkName;
        this.drinkPrice = drinkPrice;
    }

    @Override
    public double consume() {
        System.out.println("消费:" +  drinkName + ", 费用：" + drinkPrice);

        return this.drinkPrice;
    }

}
