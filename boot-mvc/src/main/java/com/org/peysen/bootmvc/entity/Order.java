package com.org.peysen.bootmvc.entity;

import java.util.Date;
import java.util.List;

/**
 * @Description: java类作用描述
 * Created by mengmeng.Pei
 * 2019/8/8 9:21
 */
public class Order {
    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 预定时间 默认情况下的日期类型也无法进行封装 需要添加自定义的预定时间
     */
    private Date bookingTime;

    /**
     * 订单详情列表，controller封装的order对象中如果没有自定义的类型转换，默认情况下无法正确的封装
     */
    private List<OrderDetail> orderDetailList;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderNo='" + orderNo + '\'' +
                ", bookingTime=" + bookingTime +
                '}';
    }
}
