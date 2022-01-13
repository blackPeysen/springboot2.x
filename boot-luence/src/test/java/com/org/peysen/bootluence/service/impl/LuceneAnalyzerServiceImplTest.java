package com.org.peysen.bootluence.service.impl;

import com.org.peysen.bootluence.BootLuenceApplicationTests;
import com.org.peysen.bootluence.service.ILuceneAnalyzerService;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Author: peimengmeng
 * Date: 2022/1/13 10:24
 * Desc:
 */
public class LuceneAnalyzerServiceImplTest extends BootLuenceApplicationTests {
    @Autowired
    private ILuceneAnalyzerService luceneAnalyzerService;

    @Test
    public void analyze() {
        String content = "quick";
        luceneAnalyzerService.analyze(content);
    }
}