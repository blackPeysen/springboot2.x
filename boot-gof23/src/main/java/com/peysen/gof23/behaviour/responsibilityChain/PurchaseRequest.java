package com.peysen.gof23.behaviour.responsibilityChain;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/17 14:32
 * @Desc: 采购请求
 */

public class PurchaseRequest {
    private int id;
    private String name;
    private float price;

    public PurchaseRequest(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "PurchaseRequest{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
