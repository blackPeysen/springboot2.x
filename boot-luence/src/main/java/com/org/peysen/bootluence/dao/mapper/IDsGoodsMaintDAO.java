package com.org.peysen.bootluence.dao.mapper;

import com.org.peysen.bootluence.entity.DsGoods;
import org.apache.ibatis.annotations.Param;

/**
 * Author: peimengmeng
 * Date: 2022/1/5 10:04
 * Desc:
 */
public interface IDsGoodsMaintDAO {

    DsGoods getGoodsBasicInfo(@Param("goodsId") Long goodsId);

}
