package com.org.peysen.bootluence.dao.mapper;

import com.org.peysen.bootluence.entity.DsGoodsAmz;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;

/**
 * Author: peimengmeng
 * Date: 2021/10/13 17:09
 * Desc:
 */
@Repository
public interface IDsGoodsAmzDAO {

    @MapKey("goodsId")
    Map<Long, DsGoodsAmz> getDsGoodsAmzMap(@Param("goodsIds") Collection<Long> goodsIds);

    DsGoodsAmz getGoodsAmzByGoodsId(@Param("goodsId") Long goodsId);

}
