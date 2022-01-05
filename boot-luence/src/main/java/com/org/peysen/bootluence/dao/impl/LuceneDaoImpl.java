package com.org.peysen.bootluence.dao.impl;

import com.org.peysen.bootluence.dao.ILuceneDao;
import com.org.peysen.bootluence.entity.DsGoods;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: peimengmeng
 * Date: 2022/1/4 11:28
 * Desc:
 */
@Repository(value = "luceneDao")
@Slf4j
public class LuceneDaoImpl implements ILuceneDao {

    @Autowired
    private Analyzer analyzer;
    @Autowired
    private IndexWriter indexWriter;
    @Autowired
    private SearcherManager searcherManager;

    @Override
    public void addProductIndex(DsGoods dsGoods) throws IOException {
        log.info("新增商品索引..............");

        Document doc = createGoodsDocument(dsGoods);
        indexWriter.addDocument(doc);
        indexWriter.commit();
    }

    @Override
    public void createProductIndex(List<DsGoods> productList) throws IOException {
        log.info("商品初始创建索引中..............");

        List<Document> docs = new ArrayList<>();
        for (DsGoods p : productList) {
            Document doc = createGoodsDocument(p);
            docs.add(doc);
        }
        indexWriter.addDocuments(docs);
        indexWriter.commit();
    }

    /**
     * 创建商品对应的文档Document
     *
     * @param dsGoods
     * @return
     */
    private Document createGoodsDocument(DsGoods dsGoods){
        Document doc = new Document();

        doc.add(new LongPoint("productId", dsGoods.getGoodsId()));
        doc.add(new StoredField("productId", dsGoods.getGoodsId()));

        doc.add(new TextField("productName", dsGoods.getGoodsName(), Field.Store.YES));
        doc.add(new TextField("keywords", dsGoods.getKeywords(), Field.Store.YES));

        return doc;
    }

    @Override
    public List<DsGoods> searchProduct(String productName) throws IOException {
        searcherManager.maybeRefresh();
        IndexSearcher indexSearcher = searcherManager.acquire();

        Term term = new Term("productName", productName);
        Query query = new TermQuery(term);

        TopDocs topDocs = indexSearcher.search(query, 1);
        if (topDocs.totalHits==0){
            return new ArrayList<>();
        }

        ScoreDoc[] hits = topDocs.scoreDocs;
        List<DsGoods> pList = new ArrayList<>();
        for (int i = 0; i < hits.length; i++) {
            Document doc = indexSearcher.doc(hits[i].doc);

            DsGoods product = new DsGoods();
            product.setGoodsId(Long.valueOf(doc.get("productId")));
            product.setGoodsName(doc.get("productName"));
            product.setKeywords(doc.get("keywords"));

            pList.add(product);
        }

        return pList;
    }

    @Override
    public void deleteProductIndexById(String productName) throws IOException {
        indexWriter.deleteDocuments(new Term("productName", productName));
        indexWriter.commit();
    }

}
