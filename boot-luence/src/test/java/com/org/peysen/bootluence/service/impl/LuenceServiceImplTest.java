package com.org.peysen.bootluence.service.impl;

import com.org.peysen.bootluence.BootLuenceApplicationTests;
import com.org.peysen.bootluence.service.ILuenceService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Author: peimengmeng
 * Date: 2022/1/4 10:14
 * Desc:
 */
public class LuenceServiceImplTest extends BootLuenceApplicationTests {
    @Autowired
    private ILuenceService luenceService;

    @Test
    public void createIndex() {
        luenceService.createIndex();
    }
}