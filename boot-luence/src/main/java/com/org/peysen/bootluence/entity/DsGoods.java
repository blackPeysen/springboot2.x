package com.org.peysen.bootluence.entity;

import lombok.Data;

import java.util.Date;

/**
 * DsGoods.java
 *
 */
@Data
public class DsGoods {
    /**
     * 主键，商品id
     */
    private Long goodsId;

    /**
     * 商户id，busi_info表主键
     */
    private Long busiId;

    /**
     * spu码
     */
    private String spuCode;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 后台类目id
     */
    private Long editCatId;

    /**
     * Amazon对应的商品类型
     */
    private String productType;

    /**
     * 商品审核状态，0 - new，1 - revised，2 - rejected，3 -approved，4 - user deleted, 5 - oss deleted，6 - suspended
     */
    private Short goodsStatus;

    /**
     * 商品销售状态，其值为：0 - 不可售，1 - 可售
     */
    private Short goodsSellStatus;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 关键词，json数组格式["aa","bb"]
     */
    private String keywords;

    /**
     * 备货期，单位天
     */
    private Integer processingTime;

    /**
     * 运输属性
     */
    private String shippingLimitations;

    /**
     * 证书ID，逗号分隔，#COM_CERIFICATE.CERIFICATE_ID
     */
    private String cerificateIds;

    /**
     * 可销售国家，逗号分隔, base_region表主键
     */
    @Deprecated
    private String availableRegions;

    /**
     * 可销售平台
     */
    private String supportedPlatforms;

    /**
     * 禁售平台字段
     * 形如 ： {"checked":["PlatfromA","PlatfromB"],"otherVal":"XXXXXXX"}
     */
    private String notSupportedPlatforms;

    /**
     * 变体参数，例如：typeid:valueid:type,typeid:valueid:type，type=0标识预置属性名：预置属性值，type=1标识预置属性名：自定义属性值 ，type=2标识自定义属性名：自定义属性值
     */
    private String variationProps;

    /**
     * 独特卖点，以json格式保存，例如：["aa","bb"]
     */
    private String sellingPoints;

    /**
     * 数据更新时间,非删除情况下的更新均更新此值
     */
    private Date dataUpdateTime;

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
    private Date spuAddTime;

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
    private Date spuUpdateTime;

    /**
     * 删除人编号
     */
    private String checkerNo;

    /**
     * 删除人姓名
     */
    private String checkerName;

    /**
     * 删除时间
     */
    private Date checkTime;

    /**
     * 中文审核意见
     */
    private String returnAdviseCn;

    /**
     * 英文审核意见
     */
    private String returnAdviseEn;

    /**
     * 删除标志，0 - 正常，1 - 已删除
     */
    private Short goodsDeleteFlag;

    /**
     * 触发展示表更新的时间
     */
    private Date supdateTime;

}
