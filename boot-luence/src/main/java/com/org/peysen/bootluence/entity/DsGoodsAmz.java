package com.org.peysen.bootluence.entity;

import lombok.Data;

import java.util.Date;

/**
 * Author: peimengmeng
 * Date: 2021/10/13 17:06
 * Desc:
 */
@Data
public class DsGoodsAmz {
    /**
     * 主键
     */
    private Long amzId;

    /**
     * 用户ID
     */
    private Long busiId;

    /**
     * 商品SPU ID
     */
    private Long goodsId;

    /**
     * 商品刊登amazon类目类型
     */
    private String productType;

    /**
     * 商品刊登amazon信息
     */
    private String amzInfo;

    /**
     * 删除标志，0 - 正常，1 - 已删除
     */
    private Short deleteFlag;

    /**
     * 添加人编号
     */
    private String adderNo;

    /**
     * 添加人姓名
     */
    private String adderName;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 更新人编号
     */
    private String updaterNo;

    /**
     * 更新人姓名
     */
    private String updaterName;

    /**
     * 更新时间
     */
    private Date updateTime;

    @Override
    public String toString() {
        return "DsGoodsAmz{" +
                "amzId=" + amzId +
                ", goodsId=" + goodsId +
                ", amzInfo='" + amzInfo + '\'' +
                ", productInfo='" + productType + '\'' +
                ", deleteFlag=" + deleteFlag +
                ", adderNo='" + adderNo + '\'' +
                ", adderName='" + adderName + '\'' +
                ", addTime=" + addTime +
                ", updaterNo='" + updaterNo + '\'' +
                ", updaterName='" + updaterName + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
