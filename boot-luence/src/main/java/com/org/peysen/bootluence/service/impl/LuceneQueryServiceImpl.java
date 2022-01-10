package com.org.peysen.bootluence.service.impl;

import com.org.peysen.bootluence.dao.ILuceneDao;
import com.org.peysen.bootluence.entity.DsGoods;
import com.org.peysen.bootluence.service.ILeceneQueryService;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.org.peysen.bootluence.constants.ProductConstants.*;

/**
 * Author: peimengmeng
 * Date: 2022/1/7 9:33
 * Desc:
 */
@Service
public class LuceneQueryServiceImpl implements ILeceneQueryService {

    @Autowired
    private ILuceneDao luceneDao;


    @Override
    public List<DsGoods> searchProductByTerm(String productName) throws IOException, ParseException {
        return luceneDao.searchProductByTerm(PRODUCT_NAME_FIELD, productName);
    }

    @Override
    public List<DsGoods> searchProductByTermRange(String productName) throws IOException, ParseException {
        return luceneDao.searchProductByTermRange(KEYWORDS_FIELD, productName);
    }

    @Override
    public List<DsGoods> searchProductByNumberRange(int lowerValue, int upperValue) throws IOException, ParseException {
        return luceneDao.searchProductByNumberRange(PROCESSINGTIME_FIELD, lowerValue, upperValue);
    }

    @Override
    public List<DsGoods> searchProductByPrefix(String prefix) throws IOException {
        return luceneDao.searchProductByPrefix(PRODUCT_NAME_FIELD, prefix);
    }

    @Override
    public List<DsGoods> searchProductByBoolean(String fieldName, String prefix) throws IOException {
        return luceneDao.searchProductByBoolean(PRODUCT_NAME_FIELD, prefix);
    }

    @Override
    public List<DsGoods> searchProductByParser(String productName) throws IOException, ParseException {
        return luceneDao.searchProductByParser(PRODUCT_NAME_FIELD, productName);
    }

}
