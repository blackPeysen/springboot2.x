package com.org.peysen.bootmongodb.domain;

import lombok.Data;

/**
 * @Auther: peimengmeng
 * @Date: 2022/1/8_16:55
 * @Desc:
 */
@Data
public class ProductSize {

    private double weight;

    private String weightUnit;

    private String modelNum;

    private String manufacturer;

    private String color;
}
