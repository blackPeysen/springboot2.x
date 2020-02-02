package com.org.peysen.bootSharding.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @Author : mengmeng.pei
 * @Date : 2020/2/1
 * @Desc :
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OrderDaoTest {
    @Autowired
    OrderDao orderDao;

    @Test
    public void testInsertDao() {
        for(int i=1; i<10;i++){
            orderDao.insertDao(new BigDecimal(11), i, "Success");
        }
    }

    @Test
    public void testSelectOrder() {
        List<Long> ids = new ArrayList<>();
        ids.add(430461716568997888L);
        ids.add(430461716631912449L);

        List<Map> maps = orderDao.selectOrder(ids);
        Assert.assertEquals(2, maps.size());
    }
}