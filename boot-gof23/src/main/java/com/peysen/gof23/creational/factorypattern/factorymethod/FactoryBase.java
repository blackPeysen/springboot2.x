package com.peysen.gof23.creational.factorypattern.factorymethod;

import com.peysen.gof23.creational.factorypattern.IProduct;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/8 10:10
 * @Desc:
 */
public abstract class FactoryBase {

    protected abstract IProduct createProduct();

}
