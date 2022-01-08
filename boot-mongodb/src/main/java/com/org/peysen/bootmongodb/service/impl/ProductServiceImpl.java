package com.org.peysen.bootmongodb.service.impl;

import com.org.peysen.bootmongodb.domain.Product;
import com.org.peysen.bootmongodb.repository.ProductRepository;
import com.org.peysen.bootmongodb.service.IProductService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public ObjectId saveProduct(Product product) {
        if (isNull(product)){
            return null;
        }
        Product save = productRepository.save(product);
        return save.getId();
    }

    @Override
    public List<Product> queryProduct(String productName) {
        return productRepository.findByProductName(productName);
    }

    /**
     * 复杂查询，通过Criteria和Pageable进行分页，排序
     *
     * @return
     */
    public Page<Product> pageQueryProduct(){
        Pageable pageable = PageRequest.of(0, 5);

        Query query = new Query();
        Criteria criteria = Criteria.where("productName").is("Extra Large WheelBarrow")
                .and("skuCode").is("9092")
                .andOperator(Criteria.where("totalReviews").gt(3),
                        Criteria.where("productSize.weight").gt(45));

        query.addCriteria(criteria);

        long count = mongoTemplate.count(query, Product.class);

        List<Product> products = mongoTemplate.find(query.with(pageable), Product.class);

        return new PageImpl<>(products, pageable, count);
    }
}
