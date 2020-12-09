package com.peysen.gof23.structural.decorator;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/9 11:08
 * @Desc:
 */
public abstract class Decorator extends AbstractDrink {
    protected AbstractDrink drink;

    public Decorator(AbstractDrink drink) {
        super(drink.drinkName, drink.drinkPrice);
        this.drink = drink;
    }

    protected double getConsumePrice(){
        double drinkPrice = this.drink.consume();

        double extraCost = extraCost();

        return drinkPrice + extraCost;
    }

    protected  abstract double extraCost();


}
