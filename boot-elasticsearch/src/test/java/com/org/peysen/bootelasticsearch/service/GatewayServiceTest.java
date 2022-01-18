package com.org.peysen.bootelasticsearch.service;

import com.org.peysen.bootelasticsearch.BootElasticsearchApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Author: peimengmeng
 * Date: 2022/1/18 16:19
 * Desc:
 */
public class GatewayServiceTest extends BootElasticsearchApplicationTests {
    @Autowired
    private GatewayService gatewayService;

    @Test
    public void search() {
        gatewayService.search();
    }
}