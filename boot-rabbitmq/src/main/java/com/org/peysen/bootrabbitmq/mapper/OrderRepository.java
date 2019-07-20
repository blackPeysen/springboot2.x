package com.org.peysen.bootrabbitmq.mapper;

import com.org.peysen.bootrabbitmq.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: Order DAO
 * @Author: peysen
 * @CreateDate: 2019/7/8 21:36
 * @UpdateRemark: The modified content
 */

@Mapper
public interface OrderRepository {

    Integer insertOrder(Order order);

}
