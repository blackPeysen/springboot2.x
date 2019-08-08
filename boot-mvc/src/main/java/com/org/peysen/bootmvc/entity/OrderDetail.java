package com.org.peysen.bootmvc.entity;

import java.util.Date;

/**
 * @Description: java类作用描述
 * Created by mengmeng.Pei
 * 2019/8/8 9:22
 */
public class OrderDetail {
    private Long productId;

    private String productName;

    /**
     * 日期类型
     */
    private Date buyTime;

    public OrderDetail() {
    }

    public OrderDetail(Long productId, String productName, Date buyTime) {
        this.productId = productId;
        this.productName = productName;
        this.buyTime = buyTime;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", buyTime=" + buyTime +
                '}';
    }
}
