package com.peysen.gof23.creational.factorypattern.simplefactory;

import com.peysen.gof23.creational.factorypattern.IProduct;
import com.peysen.gof23.creational.factorypattern.Keyboard;
import com.peysen.gof23.creational.factorypattern.Mouse;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/8 10:07
 * @Desc:
 */
public class Factory {

    public static IProduct createProduct(String type){
        if (type.isEmpty()){
            return null;
        }
        if ("mouse".equalsIgnoreCase(type)){
         return new Mouse();
        } else if ("key".equalsIgnoreCase(type)){
            return new Keyboard();
        }
        return null;
    }

}
