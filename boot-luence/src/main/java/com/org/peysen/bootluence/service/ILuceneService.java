package com.org.peysen.bootluence.service;

import com.org.peysen.bootluence.entity.DsGoods;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;
import java.util.List;

/**
 * Author: peimengmeng
 * Date: 2022/1/4 9:35
 * Desc:
 */
public interface ILuceneService {

    void createIndex(Long goodsId);

    /**
     * 小程序项目启动后将同步Product表,并创建index
     * 全量更新商品索引
     * @throws IOException
     */
    void synProductCreatIndex(Long busiId) throws IOException;


    /**
     *
     * @param productName
     * @return
     * @throws IOException
     * @throws ParseException
     */
    List<DsGoods> searchProduct(String productName) throws IOException, ParseException;

    /**
     * 通过id删除商品索引
     * @param id
     * @throws IOException
     */
    void deleteProductIndexById(String id) throws IOException;
}
