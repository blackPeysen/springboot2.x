package com.org.peysen.bootmongodb.service.impl;

import com.org.peysen.bootmongodb.domain.Product;
import com.org.peysen.bootmongodb.repository.ProductRepository;
import com.org.peysen.bootmongodb.service.IProductService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

/**
 * @Auther: peimengmeng
 * @Date: 2022/1/7_07:35
 * @Desc:
 */
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ObjectId saveProduct(Product product) {
        if (isNull(product)){
            return null;
        }
        Product save = productRepository.save(product);
        return save.getId();
    }

    @Override
    public Product queryProduct(String productName) {
        return null;
    }
}
