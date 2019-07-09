package com.org.peysen.bootrabbitmq.entity;

import java.io.Serializable;

/**
 * @Description: 订单实体类
 * @Author: peysen
 * @CreateDate: 2019/7/7 18:48
 * @UpdateRemark: The modified content
 */
public class Order implements Serializable {

    private Integer id;

    private String context;

    private String messageId;


    public Order() { }

    public Order(Integer id, String context, String messageId) {
        this.id = id;
        this.context = context;
        this.messageId = messageId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", context='" + context + '\'' +
                ", messageId='" + messageId + '\'' +
                '}';
    }
}
