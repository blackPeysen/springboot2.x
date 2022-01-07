package com.org.peysen.bootluence.service;

import com.org.peysen.bootluence.entity.DsGoods;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;
import java.util.List;

/**
 * Author: peimengmeng
 * Date: 2022/1/7 9:32
 * Desc:
 */
public interface ILeceneQueryService {
    /**
     *
     * @param productName
     * @return
     * @throws IOException
     * @throws ParseException
     */
    List<DsGoods> searchProductByTerm(String productName) throws IOException, ParseException;

    /**
     * 查询索引
     * @param productName
     * @return
     * @throws IOException
     * @throws ParseException
     */
    List<DsGoods> searchProductByTermRange(String productName) throws IOException, ParseException;


    /**
     * 查询索引
     * @param lowerValue
     * @return
     * @throws IOException
     * @throws ParseException
     */
    List<DsGoods> searchProductByNumberRange(int lowerValue, int upperValue) throws IOException, ParseException;

    /**
     * 查询索引
     * @param prefix
     * @return
     * @throws IOException
     * @throws ParseException
     */
    List<DsGoods> searchProductByPrefix(String prefix) throws IOException;


    /**
     * 查询索引
     * @param fieldName
     * @return
     * @throws IOException
     * @throws ParseException
     */
    List<DsGoods> searchProductByBoolean(String fieldName, String prefix) throws IOException;

    /**
     *
     * @param productName
     * @return
     * @throws IOException
     * @throws ParseException
     */
    List<DsGoods> searchProductByParser(String productName) throws IOException, ParseException;
}
