package com.org.peysen.bootluence.service.impl;

import com.org.peysen.bootluence.BootLuenceApplicationTests;
import com.org.peysen.bootluence.entity.DsGoods;
import com.org.peysen.bootluence.service.IDsGoodsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Author: peimengmeng
 * Date: 2022/1/5 9:27
 * Desc:
 */
public class DsGoodsServiceImplTest extends BootLuenceApplicationTests {
    @Autowired
    private IDsGoodsService dsGoodsService;

    @Test
    public void getGoodsTest() {
        DsGoods dsGoods = dsGoodsService.getDsGoods(1619752L);

        System.out.println(dsGoods.toString());
    }
}