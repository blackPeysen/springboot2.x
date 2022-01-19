package com.org.peysen.bootelasticsearch.service;

import com.org.peysen.bootelasticsearch.BootElasticsearchApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Author: peimengmeng
 * Date: 2022/1/19 10:41
 * Desc:
 */
public class EsStatisticServiceTest extends BootElasticsearchApplicationTests {
    @Autowired
    private EsStatisticService esStatisticService;

    @Test
    public void existsIndex() throws IOException {
        esStatisticService.existsIndex();
    }

    @Test
    public void searchTest() throws IOException {
        esStatisticService.search("2021122", "2323");
    }
}