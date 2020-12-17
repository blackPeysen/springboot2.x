package com.peysen.gof23.structural.decorator;

import com.peysen.gof23.structural.decorator.drink.AbstractDrink;
import com.peysen.gof23.structural.decorator.flavour.AbstractFlavour;
import com.peysen.gof23.structural.decorator.packake.AbstractPackage;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/9 11:12
 * @Desc: 包装盒装饰器
 */
public class PackageDecorator extends Decorator {
    private List<AbstractPackage> packageList = new ArrayList<>();

    public PackageDecorator(AbstractDrink drink) {
        super(drink);
    }

    @Override
    protected double extraCost() {
        return packageList.stream().mapToDouble(AbstractPackage::getPackagePrice).sum();
    }

    public void addPackage(AbstractPackage abstractPackage) {
        packageList.add(abstractPackage);
    }
}
