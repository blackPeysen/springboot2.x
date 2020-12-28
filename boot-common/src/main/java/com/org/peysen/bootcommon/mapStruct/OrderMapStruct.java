package com.org.peysen.bootcommon.mapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @Author: peimengmeng
 * @Date: 2020/12/28 13:47
 * @Desc: MapStruct接口，用于属性拷贝
 *      通过source 和 targer 可以用于属性名不一样的赋值操作
 */

@Mapper
public interface OrderMapStruct {

    @Mappings({
            @Mapping(source = "orderSn", target = "orderSn"),
            @Mapping(source = "receiverKeyword", target = "receiverKey")
    })
    OrderQueryParam  orderToOrderQueryParam(Order order);

}
