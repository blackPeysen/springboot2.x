package com.org.peysen.bootrabbitmq.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 消息信息实体类
 * @Author: peysen
 * @CreateDate: 2019/7/9 22:06
 * @UpdateRemark: The modified content
 */
public class MessageInfo implements Serializable {
    private Integer messageId;

    private String messageContext;

    private Integer tryCount;

    private Integer status;

    private Date nextRetry;

    private Date createTime;

    private Date updateTime;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessageContext() {
        return messageContext;
    }

    public void setMessageContext(String messageContext) {
        this.messageContext = messageContext;
    }

    public Integer getTryCount() {
        return tryCount;
    }

    public void setTryCount(Integer tryCount) {
        this.tryCount = tryCount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getNextRetry() {
        return nextRetry;
    }

    public void setNextRetry(Date nextRetry) {
        this.nextRetry = nextRetry;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "MessageInfo{" +
                "messageId=" + messageId +
                ", messageContext='" + messageContext + '\'' +
                ", tryCount=" + tryCount +
                ", status=" + status +
                ", nextRetry=" + nextRetry +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
