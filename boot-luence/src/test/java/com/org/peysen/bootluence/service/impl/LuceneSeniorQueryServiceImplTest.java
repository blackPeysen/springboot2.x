package com.org.peysen.bootluence.service.impl;

import com.org.peysen.bootluence.BootLuenceApplicationTests;
import com.org.peysen.bootluence.entity.DsGoods;
import com.org.peysen.bootluence.service.ILuceneSeniorQueryService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

/**
 * Author: peimengmeng
 * Date: 2022/1/10 10:46
 * Desc:
 */
public class LuceneSeniorQueryServiceImplTest extends BootLuenceApplicationTests {
    @Autowired
    private ILuceneSeniorQueryService luceneSeniorQueryService;

    @Test
    public void searchProductBySort() throws IOException {
        List<DsGoods> dsGoodsList = luceneSeniorQueryService.searchProductBySort("tables");

        for (DsGoods dsGoods : dsGoodsList){
            System.out.println(dsGoods.getProcessingTime());
        }
    }

    @Test
    public void searchProductBySpanTest() throws IOException {
        List<DsGoods> dsGoodsList = luceneSeniorQueryService.searchProductBySpanTerm("tables");

        for (DsGoods dsGoods : dsGoodsList){
            System.out.println(dsGoods.toString());
        }
    }

    @Test
    public void searchProductBySpanFirstTest() throws IOException {
        List<DsGoods> dsGoodsList = luceneSeniorQueryService.searchProductBySpanFirst("tables");

        for (DsGoods dsGoods : dsGoodsList){
            System.out.println(dsGoods.toString());
        }
    }
}