package com.org.peysen.bootluence.service;

import com.org.peysen.bootluence.entity.DsGoods;

/**
 * Author: peimengmeng
 * Date: 2021/11/26 10:43
 * Desc:
 */
public interface IDsGoodsService {

    /**
     * 查询商品刊登Amazon信息
     *
     * @param goodsId
     * @return
     */
    DsGoods getDsGoods(Long goodsId);

}
