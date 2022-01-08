package com.org.peysen.bootmongodb.domain;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * @Auther: peimengmeng
 * @Date: 2022/1/6_21:07
 * @Desc:
 */

@Data
@Document(collection = "t_product")
public class Product {
    @Id
    private ObjectId id;

    private String slug;

    private String skuCode;

    private String productName;

    private String desc;

    private int totalReviews;

    private double averageReviews;

    private List<String> tags;

    private ProductSize productSize;

    /**
     * 创建索引，TTL集合
     */
    @Indexed(expireAfterSeconds = 3600)
    private Date createDate;
}
