package com.peysen.gof23.structural.decorator;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/9 11:00
 * @Desc: 使用装饰器模式实现不同饮品 添加 不同配料，计算价格
 */
public class Cilent {
    public static void main(String[] args) {
        AbstractDrink drink = new Coffee("咖啡", 10.0d);
        AbstractFlavour flavour = new Sugar("糖份", 3.0d);

        FlavourDecorator decorator = new FlavourDecorator(drink);
        decorator.addFlavour(flavour);
        decorator.addFlavour(flavour);

        System.out.println(decorator.getConsumePrice());
    }
}
