package com.org.peysen.bootluence.service.impl;

import com.org.peysen.bootluence.entity.DsGoods;
import com.org.peysen.bootluence.service.ILuceneSeniorQueryService;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.TermFrequencyAttribute;
import org.apache.lucene.analysis.tokenattributes.TermFrequencyAttributeImpl;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.IntPoint;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.LeafReaderContext;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.search.spans.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.org.peysen.bootluence.constants.ProductConstants.PROCESSINGTIME_FIELD;
import static com.org.peysen.bootluence.constants.ProductConstants.PRODUCT_NAME_FIELD;
import static org.apache.lucene.search.DocIdSetIterator.NO_MORE_DOCS;

/**
 * Author: peimengmeng
 * Date: 2022/1/7 9:33
 * Desc:
 */
@Service
public class LuceneSeniorQueryServiceImpl implements ILuceneSeniorQueryService {
    @Autowired
    private Analyzer analyzer;
    @Autowired
    private IndexWriter indexWriter;
    @Autowired
    private SearcherManager searcherManager;

    @Override
    public List<DsGoods> searchProductBySort(String productName) throws IOException {
        searcherManager.maybeRefresh();
        IndexSearcher indexSearcher = searcherManager.acquire();

        Query multiPhraseQuery = new MultiPhraseQuery.Builder()
                .add(new Term(PRODUCT_NAME_FIELD, productName))
                .add(new Term(PRODUCT_NAME_FIELD, "model"))
                .build();
        Query rangeQuery = IntPoint.newRangeQuery(PROCESSINGTIME_FIELD, 0, 6);

        BooleanQuery booleanQuery = new BooleanQuery.Builder()
                .add(multiPhraseQuery, BooleanClause.Occur.SHOULD)
                .add(rangeQuery, BooleanClause.Occur.SHOULD)
                .build();

        /**
         * FieldComparatorSource 自定义排序规则
         */
        SortField sortField = new SortField(PRODUCT_NAME_FIELD, new FieldComparatorSource() {
            @Override
            public FieldComparator<?> newComparator(String fieldname, int numHits, int sortPos, boolean reversed) {
                return null;
            }
        });

        /**
         * 定义排序:
         *  可以传入一个SortField，也可以传入多个SortField，跟order by一致
         */
        Sort sort = new Sort(sortField, SortField.FIELD_SCORE,
                            new SortField(PROCESSINGTIME_FIELD, SortField.Type.INT, false));

        TopDocs topDocs = indexSearcher.search(booleanQuery, 10, sort);

        if (topDocs.totalHits==0){
            return new ArrayList<>();
        }

        return getDsGoodsList(booleanQuery, topDocs.scoreDocs, indexSearcher);
    }

    /**
     * SpanTermQuery 同 TermQuery
     *
     * @param productName
     * @return
     * @throws IOException
     */
    @Override
    public List<DsGoods> searchProductBySpanTerm(String productName) throws IOException {
        searcherManager.maybeRefresh();
        IndexSearcher indexSearcher = searcherManager.acquire();

        SpanQuery query = new SpanTermQuery(new Term(PRODUCT_NAME_FIELD, "bar"));

        TopDocs topDocs = indexSearcher.search(query, 10);

        return getSpansDsGoodsList(query, topDocs, indexSearcher);
    }

    @Override
    public List<DsGoods> searchProductBySpanFirst(String productName) throws IOException {
        searcherManager.maybeRefresh();
        IndexSearcher indexSearcher = searcherManager.acquire();

        SpanQuery baseSpanQuery = new SpanTermQuery(new Term(PRODUCT_NAME_FIELD, "bar"));
        SpanFirstQuery spanFirstQuery = new SpanFirstQuery(baseSpanQuery, 4);

        TopDocs topDocs = indexSearcher.search(spanFirstQuery, 10);

        return getSpansDsGoodsList(spanFirstQuery, topDocs, indexSearcher);
    }


    private List<DsGoods> getSpansDsGoodsList(SpanQuery query, TopDocs topDocs, IndexSearcher indexSearcher) throws IOException {
        if (topDocs.totalHits==0){
            return new ArrayList<>();
        }

        List<LeafReaderContext> leaves = indexSearcher.getTopReaderContext().leaves();
        LeafReaderContext leafReaderContext = leaves.get(0);
        Spans spans = query.createWeight(indexSearcher, true, 0.0f)
                .getSpans(leafReaderContext, SpanWeight.Postings.OFFSETS);

        Map<Integer, Float> docScoreMap = Stream.of(topDocs.scoreDocs).collect(Collectors.toMap(scoreDoc -> scoreDoc.doc, scoreDoc -> scoreDoc.score));

        while (spans.nextDoc() != NO_MORE_DOCS){
            int docID = spans.docID();
            Document doc = indexSearcher.doc(docID);

            StringBuilder builder = new StringBuilder();
            builder.append("docId:").append(docID)
                    .append(", productName:").append(doc.get(PRODUCT_NAME_FIELD))
                    .append(", [Scored:").append(docScoreMap.get(docID));

            System.out.println("builer: " + builder);
        }


        return getDsGoodsList(query, topDocs.scoreDocs, indexSearcher);
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
