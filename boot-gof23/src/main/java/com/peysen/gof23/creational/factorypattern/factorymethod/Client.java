package com.peysen.gof23.creational.factorypattern.factorymethod;

import com.peysen.gof23.creational.factorypattern.IProduct;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/8 10:14
 * @Desc:
 */
public class Client {
    public static void main(String[] args) {
        IProduct product = new MouseFactory().createProduct();
        System.out.println(product.getProductInfo());

        product = new KeyBoardFactory().createProduct();
        System.out.println(product.getProductInfo());
    }
}
