package com.peysen.gof23.structural.decorator;

import com.peysen.gof23.structural.decorator.drink.AbstractDrink;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/9 11:08
 * @Desc:
 */
public abstract class Decorator extends AbstractDrink {
    protected AbstractDrink drink;

    public Decorator(AbstractDrink drink) {
        super(drink.getDrinkName(), drink.getDrinkPrice());
        this.drink = drink;
    }

    @Override
    public double consume(){
        double drinkPrice = this.drink.consume();

        double extraCost = extraCost();

        return drinkPrice + extraCost;
    }

    protected  abstract double extraCost();


}
