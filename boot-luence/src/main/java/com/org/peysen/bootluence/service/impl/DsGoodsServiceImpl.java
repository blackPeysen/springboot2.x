package com.org.peysen.bootluence.service.impl;

import com.google.common.collect.Lists;
import com.org.peysen.bootluence.dao.mapper.IDsGoodsMaintDAO;
import com.org.peysen.bootluence.entity.DsGoods;
import com.org.peysen.bootluence.service.IDsGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

/**
 * Author: peimengmeng
 * Date: 2021/11/26 19:07
 * Desc:
 */
@Service
public class DsGoodsServiceImpl implements IDsGoodsService {
    @Autowired
    private IDsGoodsMaintDAO dsGoodsMaintDAO;

    @Override
    public DsGoods getDsGoods(Long goodsId) {
        if (isNull(goodsId)){
            return null;
        }
        return dsGoodsMaintDAO.getGoodsBasicInfo(goodsId);
    }

    @Override
    public List<DsGoods> getDsGoodsList(Long busiId) {
        if (isNull(busiId)){
            return Lists.newArrayList();
        }

        return dsGoodsMaintDAO.listDsGoods(busiId);
    }

}
