package com.peysen.gof23.structural.decorator;

import com.peysen.gof23.structural.decorator.drink.AbstractDrink;
import com.peysen.gof23.structural.decorator.drink.Coffee;
import com.peysen.gof23.structural.decorator.flavour.AbstractFlavour;
import com.peysen.gof23.structural.decorator.flavour.Sugar;
import com.peysen.gof23.structural.decorator.packake.CartonPackage;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/9 11:00
 * @Desc: 使用装饰器模式实现不同饮品 添加 不同配料，计算价格
 */
public class Cilent {
    public static void main(String[] args) {
        AbstractDrink drink = new Coffee("咖啡", 10.0d);
        AbstractFlavour flavour = new Sugar("糖份", 3.0d);

        System.out.println("一份咖啡：" + drink.consume());

        FlavourDecorator decorator = new FlavourDecorator(drink);
        decorator.addFlavour(flavour);
        decorator.addFlavour(flavour);
        System.out.println("加两份糖：" + decorator.consume());

        PackageDecorator packageDecorator = new PackageDecorator(decorator);
        packageDecorator.addPackage(new CartonPackage());
        System.out.println("加一份纸盒包装：" + packageDecorator.consume());
    }
}
