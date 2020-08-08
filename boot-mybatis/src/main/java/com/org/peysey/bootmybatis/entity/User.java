package com.org.peysey.bootmybatis.entity;

import lombok.Data;

/**
 * @Description: java类作用描述
 * Created by mengmeng.Pei
 * 2019/9/18 10:05
 */

@Data
public class User {

    private Integer id;

    private String name;

    //逻辑删除标识（0：未删除，1：已删除）
    private Integer deleted;
}
