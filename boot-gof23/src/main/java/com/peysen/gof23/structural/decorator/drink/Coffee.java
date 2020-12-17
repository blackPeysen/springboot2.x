package com.peysen.gof23.structural.decorator.drink;

import com.peysen.gof23.structural.decorator.drink.AbstractDrink;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/9 11:03
 * @Desc:
 */
public class Coffee extends AbstractDrink {

    public Coffee(String drinkName, double drinkPrice) {
        super(drinkName, drinkPrice);
    }

}
