package com.org.peysen.bootmongodb.service.impl;

import com.org.peysen.bootmongodb.BootMongodbApplicationTests;
import com.org.peysen.bootmongodb.domain.Product;
import com.org.peysen.bootmongodb.service.IProductService;
import org.assertj.core.util.Lists;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @Auther: peimengmeng
 * @Date: 2022/1/7_07:38
 * @Desc:
 */
public class ProductServiceImplTest extends BootMongodbApplicationTests {
    @Autowired
    private IProductService productService;

    @Test
    public void saveProduct() {
        Product product = new Product();
        product.setSlug("wheelbarrow-9092");
        product.setSkuCode("9092");
        product.setProductName("Extra Large WheelBarrow");
        product.setDesc("Heavy duty wheelbarrow...");
        product.setTotalReviews(4);
        product.setAverageReviews(4.5d);
        product.setTags(Lists.newArrayList("tools", "gardening", "soil"));

        ObjectId objectId = productService.saveProduct(product);
        System.out.println(objectId.toString());
    }

    @Test
    public void queryProduct() {
    }
}