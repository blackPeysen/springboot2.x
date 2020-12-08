package com.peysen.gof23.creational.factorypattern.abstractfactory;

import com.peysen.gof23.creational.factorypattern.IColor;
import com.peysen.gof23.creational.factorypattern.IProduct;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/8 10:19
 * @Desc:
 */
public abstract class AbstractFactory {

    abstract IProduct createProduct();

    abstract IColor createColor();

}
