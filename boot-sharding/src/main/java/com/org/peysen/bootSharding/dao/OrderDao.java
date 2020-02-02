package com.org.peysen.bootSharding.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Author : mengmeng.pei
 * @Date : 2020/2/1
 * @Desc :
 */

@Mapper
@Component
public interface OrderDao {

    @Insert("insert t_order(price,userId,status) values(#{price}, #{userId}, #{status})")
    int insertDao(@Param("price") BigDecimal price, @Param("userId")Integer userId, @Param("status")String status);


    @Select("<script>" +
            "select * from t_order t"+
            " where order_id in" +
            "<foreach collection='orderIds' open='(' separator=',' close=')' item='id'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    List<Map>  selectOrder(@Param("orderIds")List<Long> orderIds);

}
