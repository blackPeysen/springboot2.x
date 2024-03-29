package com.org.peysen.bootelasticsearch.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetMappingsRequest;
import org.elasticsearch.client.indices.GetMappingsResponse;
import org.elasticsearch.common.Strings;
import org.elasticsearch.index.query.*;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.*;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ParsedCardinality;
import org.elasticsearch.search.aggregations.metrics.ParsedSum;
import org.elasticsearch.search.aggregations.metrics.ParsedValueCount;
import org.elasticsearch.search.aggregations.metrics.SumAggregationBuilder;
import org.elasticsearch.search.aggregations.pipeline.BucketSortPipelineAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class EsStatisticService {
    @Autowired
    private RestHighLevelClient highLevelClient;


    public void existsIndex() throws IOException {
        IndicesClient indices = highLevelClient.indices();

        GetIndexRequest request = new GetIndexRequest("get-togher");
        boolean exists = indices.exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);


        String indexName = "swcrov_endpoint_isv_stat-20220113";
        GetMappingsRequest getMappingsRequest = new GetMappingsRequest();
        getMappingsRequest.indices(indexName);
        GetMappingsResponse mapping = indices.getMapping(getMappingsRequest, RequestOptions.DEFAULT);
        System.out.println(mapping.toString());
    }

    /**
     *  查询某时间段内，调用过API接口的商户appKey有哪些，及对应的调用总次数。
     *  页面展示时，分页查询，总条数。
     */
    public void search(String startDate, String endDate) throws IOException {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        // 设置分页
        sourceBuilder.from(0);
        sourceBuilder.size(1000);

        // 设置查询条件
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("time_bucket");
        rangeQueryBuilder.gte(startDate);
        rangeQueryBuilder.lte(endDate);

        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
        boolBuilder.must(rangeQueryBuilder);

        sourceBuilder.query(boolBuilder);

        // 设置分组聚合
        sourceBuilder.aggregation(AggregationBuilders
                .terms("app") //terms为分组后的字段名称
                .field("app_key")   //app_key进行分组
                .subAggregation(AggregationBuilders.sum("sum").field("total"))// subAggregation为子聚合
                .subAggregation(new BucketSortPipelineAggregationBuilder("sumSort", Arrays.asList(new FieldSortBuilder("sum").order(SortOrder.DESC)))
                        .from(9)
                        .size(10))
                .size(100));

        // 根据app_key求总数
        sourceBuilder.aggregation(AggregationBuilders.cardinality("appCount").field("app_key"));

        // 设置查询的索引名称
        SearchRequest searchRequest = new SearchRequest("swcrov_endpoint_isv_stat");
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        RestStatus status = searchResponse.status();

        if (status.equals(RestStatus.OK)){
            Aggregations aggregations = searchResponse.getAggregations();

            ParsedStringTerms parsedStringTerms = aggregations.get("app");
            List<? extends Terms.Bucket> buckets = parsedStringTerms.getBuckets();
            for (Terms.Bucket bucket : buckets) {
                String key = bucket.getKey().toString();
                if (StringUtils.isBlank(key)){
                    continue;
                }
                //获取数据
                Aggregations bucketAggregations = bucket.getAggregations();
                ParsedSum sumId = bucketAggregations.get("sum");

                System.out.println(key + ": " + sumId.getValue());
            }

            System.out.println("=============================");

            ParsedCardinality appCount = aggregations.get("appCount");
            System.out.println("appKeyCount: " + appCount.getValue());
        }
    }

}
