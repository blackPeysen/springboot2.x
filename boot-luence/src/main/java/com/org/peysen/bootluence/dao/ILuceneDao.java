package com.org.peysen.bootluence.dao;

import com.org.peysen.bootluence.entity.DsGoods;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;
import java.util.List;

/**
 * Author: peimengmeng
 * Date: 2022/1/4 11:24
 * Desc:
 */
public interface ILuceneDao {
    /**
     * 添加一个新索引
     *
     * @param dsGoods
     * @throws IOException
     */
    void addProductIndex(DsGoods dsGoods) throws IOException;

    /**
     * 初始化创建全部商品索引
     *
     * @param productList
     * @throws IOException
     */
    void createProductIndex(List<DsGoods> productList) throws IOException;


    /**
     * 查询索引
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
