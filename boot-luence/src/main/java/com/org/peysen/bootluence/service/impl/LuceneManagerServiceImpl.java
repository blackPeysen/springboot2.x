package com.org.peysen.bootluence.service.impl;

import com.org.peysen.bootluence.dao.ILuceneDao;
import com.org.peysen.bootluence.entity.DsGoods;
import com.org.peysen.bootluence.service.IDsGoodsService;
import com.org.peysen.bootluence.service.ILuceneManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Author: peimengmeng
 * Date: 2022/1/4 9:36
 * Desc:
 */
@Service
public class LuceneManagerServiceImpl implements ILuceneManagerService {
    @Autowired
    private ILuceneDao luceneDao;
    @Autowired
    private IDsGoodsService dsGoodsService;


    @Override
    public void createIndex(Long goodsId) {
        try {
            DsGoods goods = dsGoodsService.getDsGoods(goodsId);
            if (Objects.isNull(goods)){
                return;
            }
            luceneDao.addProductIndex(goods);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void synProductCreatIndex(Long busiId) throws IOException {
        if (Objects.isNull(busiId)){
            return;
        }

        List<DsGoods> dsGoodsList = dsGoodsService.getDsGoodsList(busiId);
        if (CollectionUtils.isEmpty(dsGoodsList)){
            return;
        }

        luceneDao.createProductIndex(dsGoodsList);
    }

    @Override
    public void updateProductIndex(Long goodsId) throws IOException {
        try {
            DsGoods goods = dsGoodsService.getDsGoods(goodsId);
            if (Objects.isNull(goods)){
                return;
            }
            luceneDao.updateProductIndex(goods);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProductIndexById(String id) throws IOException {
        luceneDao.deleteProductIndexById(id);
    }

    @Override
    public void deleleAllProduct() throws IOException {
        luceneDao.deleleAllProduct();
    }

}
