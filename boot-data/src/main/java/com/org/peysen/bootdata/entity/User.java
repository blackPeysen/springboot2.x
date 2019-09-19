package com.org.peysen.bootdata.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
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
    @TableLogic
    private Integer deleted;
}
