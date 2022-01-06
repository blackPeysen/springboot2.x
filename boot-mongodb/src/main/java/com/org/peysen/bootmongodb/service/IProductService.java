package com.org.peysen.bootmongodb.service;

import com.org.peysen.bootmongodb.domain.Product;
import org.bson.types.ObjectId;

/**
 * @Auther: peimengmeng
 * @Date: 2022/1/7_07:33
 * @Desc:
 */
public interface IProductService {

    ObjectId saveProduct(Product product);

    Product queryProduct(String productName);
}
