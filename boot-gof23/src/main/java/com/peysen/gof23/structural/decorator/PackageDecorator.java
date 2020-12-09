package com.peysen.gof23.structural.decorator;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/9 11:12
 * @Desc: 包装盒装饰器
 */
public class PackageDecorator extends Decorator {
    private List<AbstractFlavour> flavourList = new ArrayList<>();

    public PackageDecorator(AbstractDrink drink) {
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
