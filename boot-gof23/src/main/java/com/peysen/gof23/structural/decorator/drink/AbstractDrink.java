package com.peysen.gof23.structural.decorator.drink;

import com.peysen.gof23.structural.decorator.ICalculatePrice;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/9 11:02
 * @Desc:
 */
public abstract class AbstractDrink implements ICalculatePrice {

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

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public double getDrinkPrice() {
        return drinkPrice;
    }

    public void setDrinkPrice(double drinkPrice) {
        this.drinkPrice = drinkPrice;
    }
}
