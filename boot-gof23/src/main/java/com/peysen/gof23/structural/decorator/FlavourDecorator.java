package com.peysen.gof23.structural.decorator;

import com.peysen.gof23.structural.decorator.drink.AbstractDrink;
import com.peysen.gof23.structural.decorator.flavour.AbstractFlavour;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/9 11:12
 * @Desc: 调料装饰器
 */
public class FlavourDecorator extends Decorator {
    private List<AbstractFlavour> flavourList = new ArrayList<>();

    public FlavourDecorator(AbstractDrink drink) {
        super(drink);
    }

    @Override
    protected double extraCost() {
        return flavourList.stream().mapToDouble(AbstractFlavour::getFlavourPrice).sum();
    }

    public void addFlavour(AbstractFlavour flavour) {
        flavourList.add(flavour);
    }
}
