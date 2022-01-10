package com.org.peysen.bootluence.service;

import com.org.peysen.bootluence.entity.DsGoods;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;
import java.util.List;

/**
 * Author: peimengmeng
 * Date: 2022/1/10 10:22
 * Desc: 高级查询
 */
public interface ILuceneSeniorQueryService {

    /**
     * 对查询结果进行排序
     *
     * @param productName
     * @return
     * @throws IOException
     * @throws ParseException
     */
    List<DsGoods> searchProductBySort(String productName) throws IOException;



    /**
     * SpanTermQuery跨度查询, 同 TermQuery
     *
     * @param productName
     * @return
     * @throws IOException
     * @throws ParseException
     */
    List<DsGoods> searchProductBySpanTerm(String productName) throws IOException;

    /**
     * SpanFirstQuery跨度查询
     *
     * @param productName
     * @return
     * @throws IOException
     * @throws ParseException
     */
    List<DsGoods> searchProductBySpanFirst(String productName) throws IOException;

}
