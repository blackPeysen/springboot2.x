package com.org.peysen.bootluence.service.impl;

import com.org.peysen.bootluence.BootLuenceApplicationTests;
import com.org.peysen.bootluence.entity.DsGoods;
import com.org.peysen.bootluence.service.ILeceneQueryService;
import org.apache.lucene.queryparser.classic.ParseException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

/**
 * Author: peimengmeng
 * Date: 2022/1/7 9:34
 * Desc:
 */
public class LuceneQueryServiceImplTest extends BootLuenceApplicationTests {
    @Autowired
    private ILeceneQueryService leceneQueryService;

    @Test
    public void searchProductByTermTest() throws IOException, ParseException {
        List<DsGoods> dsGoodsList = leceneQueryService.searchProductByTerm("beverage04");

        System.out.println(dsGoodsList.size());
    }

    @Test
    public void searchProductByTermRangeTest() throws IOException, ParseException{
        List<DsGoods> dsGoodsList = leceneQueryService.searchProductByTermRange("bar");

        System.out.println(dsGoodsList.size());
    }

    @Test
    public void searchProductByNumberRangeTest() throws IOException, ParseException {
        List<DsGoods> dsGoodsList = leceneQueryService.searchProductByNumberRange(5, 7);

        System.out.println(dsGoodsList.size());
    }

    @Test
    public void searchProductByPrefixTest() throws IOException {
        List<DsGoods> dsGoodsList = leceneQueryService.searchProductByPrefix("food");

        System.out.println(dsGoodsList.size());
    }

    @Test
    public void searchProductByBooleanTest() throws IOException {
        List<DsGoods> dsGoodsList = leceneQueryService.searchProductByBoolean("", "food");

        System.out.println(dsGoodsList.size());
    }

    @Test
    public void searchProductByParserTest() throws IOException, ParseException {
        List<DsGoods> dsGoodsList = leceneQueryService.searchProductByParser("sets03 -keywords:bridge");

        System.out.println(dsGoodsList.size());
    }
}
