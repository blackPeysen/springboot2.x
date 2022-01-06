package com.org.peysen.bootluence.service.impl;

import com.org.peysen.bootluence.BootLuenceApplicationTests;
import com.org.peysen.bootluence.entity.DsGoods;
import com.org.peysen.bootluence.service.ILuceneService;
import org.apache.lucene.queryparser.classic.ParseException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Author: peimengmeng
 * Date: 2022/1/6 14:52
 * Desc:
 */
public class LuceneServiceImplTest extends BootLuenceApplicationTests {
    @Autowired
    private ILuceneService luenceService;

    @Test
    public void createIndex() {
        luenceService.createIndex(1619752L);
    }

    @Test
    public void deleteProductIndexByIdTest() throws IOException {
        luenceService.deleteProductIndexById("122101fuzhi");
    }

    @Test
    public void searchProductTest(){
        try {
            List<DsGoods> dsGoodsList = luenceService.searchProduct("122101fuzhi");

            System.out.println(dsGoodsList.size());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void synProductCreatIndex() throws IOException {
        luenceService.synProductCreatIndex(53282L);
    }
}