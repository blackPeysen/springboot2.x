package com.peysen.gof23.structural.decorator;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/9 11:38
 * @Desc:
 */
public abstract class AbstractFlavour {
    protected String flavourName;

    protected double flavourPrice;

    public AbstractFlavour(String flavourName, double flavourPrice) {
        this.flavourName = flavourName;
        this.flavourPrice = flavourPrice;
    }

    public String getFlavourName() {
        return flavourName;
    }

    public void setFlavourName(String flavourName) {
        this.flavourName = flavourName;
    }

    public double getFlavourPrice() {
        return flavourPrice;
    }

    public void setFlavourPrice(double flavourPrice) {
        this.flavourPrice = flavourPrice;
    }
}
