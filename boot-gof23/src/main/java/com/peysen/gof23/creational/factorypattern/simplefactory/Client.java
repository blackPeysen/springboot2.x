package com.peysen.gof23.creational.factorypattern.simplefactory;

import com.peysen.gof23.creational.factorypattern.IProduct;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/8 10:10
 * @Desc:
 */
public class Client {
    public static void main(String[] args) {
        IProduct product = Factory.createProduct("mouse");
        System.out.println(product.getProductInfo());

        product = Factory.createProduct("key");
        System.out.println(product.getProductInfo());
    }
}
