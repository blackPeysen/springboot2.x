package com.org.peysen.bootmongodb.repository;

import com.org.peysen.bootmongodb.domain.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: peimengmeng
 * @Date: 2022/1/6_21:01
 * @Desc:
 */
@Repository
public interface ProductRepository extends MongoRepository<Product, ObjectId> {

    Product findOneByProductName(String productName);

    List<Product> findByProductName(String productName);

}
