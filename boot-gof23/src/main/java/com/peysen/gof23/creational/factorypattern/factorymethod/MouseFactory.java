package com.peysen.gof23.creational.factorypattern.factorymethod;

import com.peysen.gof23.creational.factorypattern.IProduct;
import com.peysen.gof23.creational.factorypattern.Mouse;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/8 10:13
 * @Desc:
 */
public class MouseFactory extends FactoryBase {
    @Override
    protected IProduct createProduct() {
        return new Mouse();
    }
}
