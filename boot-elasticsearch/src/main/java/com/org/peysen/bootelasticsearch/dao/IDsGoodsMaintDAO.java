package com.org.peysen.bootelasticsearch.dao;

import com.org.peysen.bootelasticsearch.entity.DsGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Author: peimengmeng
 * Date: 2022/1/5 10:04
 * Desc:
 */
public interface IDsGoodsMaintDAO {

    DsGoods getGoodsBasicInfo(@Param("goodsId") Long goodsId);

    List<DsGoods> listDsGoods(@Param("busiId") Long busiId);
}
