package com.org.peysen.bootluence.dao.impl;

import com.org.peysen.bootluence.dao.ILuceneDao;
import com.org.peysen.bootluence.entity.DsGoods;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.util.BytesRef;
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
    public void updateProductIndex(DsGoods dsGoods) throws IOException {
        log.info("更新商品索引..............");

        Document doc = createGoodsDocument(dsGoods);
        indexWriter.updateDocument(new Term("productName", dsGoods.getGoodsName()), doc);
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

    @Override
    public void deleteProductIndexById(String productName) throws IOException {
        indexWriter.deleteDocuments(new Term("productName", productName));
        indexWriter.commit();
    }

    @Override
    public void deleleAllProduct() throws IOException {
        indexWriter.deleteAll();
        indexWriter.commit();
    }


    @Override
    public List<DsGoods> searchProductByTerm(String fieldName, String productName) throws IOException {
        searcherManager.maybeRefresh();
        IndexSearcher indexSearcher = searcherManager.acquire();

        Term term = new Term(fieldName, productName);
        Query query = new TermQuery(term);

        TopDocs topDocs = indexSearcher.search(query, 10);
        if (topDocs.totalHits==0){
            return new ArrayList<>();
        }

        return getDsGoodsList(query, topDocs.scoreDocs, indexSearcher);
    }

    @Override
    public List<DsGoods> searchProductByTermRange(String fieldName, String productName) throws IOException {
        searcherManager.maybeRefresh();
        IndexSearcher indexSearcher = searcherManager.acquire();

        Query query = new TermRangeQuery(fieldName, new BytesRef("a".getBytes()), new BytesRef("b".getBytes()), true, false);

        TopDocs topDocs = indexSearcher.search(query, 10);
        if (topDocs.totalHits==0){
            return new ArrayList<>();
        }

        return getDsGoodsList(query, topDocs.scoreDocs, indexSearcher);
    }

    @Override
    public List<DsGoods> searchProductByNumberRange(String fieldName, int lowerValue, int upperValue) throws IOException {
        searcherManager.maybeRefresh();
        IndexSearcher indexSearcher = searcherManager.acquire();

        Query query = IntPoint.newRangeQuery(fieldName, lowerValue, upperValue);

        TopDocs topDocs = indexSearcher.search(query, 10);
        if (topDocs.totalHits==0){
            return new ArrayList<>();
        }

        return getDsGoodsList(query, topDocs.scoreDocs, indexSearcher);
    }

    @Override
    public List<DsGoods> searchProductByPrefix(String fieldName, String prefix) throws IOException {
        searcherManager.maybeRefresh();
        IndexSearcher indexSearcher = searcherManager.acquire();

        Term term = new Term(fieldName, prefix);
        Query query = new PrefixQuery(term);

        TopDocs topDocs = indexSearcher.search(query, 10);
        if (topDocs.totalHits==0){
            return new ArrayList<>();
        }

        return getDsGoodsList(query, topDocs.scoreDocs, indexSearcher);
    }

    @Override
    public List<DsGoods> searchProductByBoolean(String fieldName, String prefix) throws IOException {
        searcherManager.maybeRefresh();
        IndexSearcher indexSearcher = searcherManager.acquire();

        Query prefixQuery = new PrefixQuery(new Term("productName", "food"));
        Query rangeQuery = IntPoint.newRangeQuery("processingTime", 5, 6);

        BooleanQuery booleanQuery = new BooleanQuery.Builder()
                .add(prefixQuery, BooleanClause.Occur.MUST)
                .add(rangeQuery, BooleanClause.Occur.MUST)
                .build();

        TopDocs topDocs = indexSearcher.search(booleanQuery, 10);
        if (topDocs.totalHits==0){
            return new ArrayList<>();
        }

        return getDsGoodsList(booleanQuery, topDocs.scoreDocs, indexSearcher);
    }

    public List<DsGoods> searchProductByParser(String fieldName, String productName) throws IOException, ParseException {
        searcherManager.maybeRefresh();
        IndexSearcher indexSearcher = searcherManager.acquire();

        QueryParser parser = new QueryParser(fieldName, analyzer);
        Query query = parser.parse(productName);

        TopDocs topDocs = indexSearcher.search(query, 10);
        if (topDocs.totalHits==0){
            return new ArrayList<>();
        }

        return getDsGoodsList(query, topDocs.scoreDocs, indexSearcher);
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

        doc.add(new IntPoint("processingTime", dsGoods.getProcessingTime()));
        doc.add(new StoredField("processingTime", dsGoods.getProcessingTime()));

        doc.add(new TextField("productName", dsGoods.getGoodsName(), Field.Store.YES));
        doc.add(new TextField("keywords", dsGoods.getKeywords(), Field.Store.YES));

        return doc;
    }

    private List<DsGoods> getDsGoodsList(Query query, ScoreDoc[] hits, IndexSearcher indexSearcher) throws IOException {
        List<DsGoods> pList = new ArrayList<>();

        for (int i = 0; i < hits.length; i++) {
            Document doc = indexSearcher.doc(hits[i].doc);
            Explanation explain = indexSearcher.explain(query, hits[i].doc);
            System.out.println("explain:" + explain.toString());

            DsGoods product = new DsGoods();
            product.setGoodsId(Long.valueOf(doc.get("productId")));
            product.setGoodsName(doc.get("productName"));
            product.setKeywords(doc.get("keywords"));
            product.setProcessingTime(Integer.valueOf(doc.get("processingTime")));

            pList.add(product);
        }

        return pList;
    }

}
