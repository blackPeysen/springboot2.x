package com.org.peysen.bootluence.service.impl;

import com.org.peysen.bootluence.BootLuenceApplicationTests;
import com.org.peysen.bootluence.service.ILuceneManagerService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Author: peimengmeng
 * Date: 2022/1/6 14:52
 * Desc:
 */
public class LuceneManagerServiceImplTest extends BootLuenceApplicationTests {
    @Autowired
    private ILuceneManagerService luceneManagerService;

    @Test
    public void createIndex() {
        luceneManagerService.createIndex(1613222L);
    }

    @Test
    public void synProductCreatIndex() throws IOException {
        luceneManagerService.synProductCreatIndex(53282L);
    }

    @Test
    public void updateProductIndexTest() throws IOException {
        luceneManagerService.updateProductIndex(1613222L);
    }

    @Test
    public void deleteProductIndexByIdTest() throws IOException {
        luceneManagerService.deleteProductIndexById("beverage04");
    }

    @Test
    public void deleteAllProductTest() throws IOException {
        luceneManagerService.deleleAllProduct();
    }
}