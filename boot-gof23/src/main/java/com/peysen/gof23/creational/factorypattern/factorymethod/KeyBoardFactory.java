package com.peysen.gof23.creational.factorypattern.factorymethod;

import com.peysen.gof23.creational.factorypattern.IProduct;
import com.peysen.gof23.creational.factorypattern.Keyboard;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/8 10:14
 * @Desc:
 */
public class KeyBoardFactory extends FactoryBase {
    @Override
    protected IProduct createProduct() {
        return new Keyboard();
    }
}
