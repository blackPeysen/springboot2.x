package com.org.peysen.bootelasticsearch.service;

import com.org.peysen.bootelasticsearch.config.ElasticRestHighLevelClient;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class EsStatisticService {
    @Autowired
    private ElasticRestHighLevelClient client;

    @Autowired
    private RestHighLevelClient highLevelClient;


    public void createIndex() throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("together");
        buildIndexMapping(createIndexRequest);

        RequestOptions options = null;
        CreateIndexResponse createIndexResponse = highLevelClient.indices().create(createIndexRequest, options);

        System.out.println(createIndexResponse.toString());
    }


    /**
     * 生成短信下发表索引结构
     *
     * createDate 创建时间
     * sendDate 发送时间
     * longCode 发送的长号码
     * mobile 下发手机号
     * corpName 发送公司名称
     * smsContent 下发短信内容
     * state 短信下发状态  0 成功 1 失败
     * operatorId  '运营商编号  1 移动 2 联通 3 电信
     * province 省份
     * ipAddr 下发服务器IP地址
     * replyTotal 短信状态报告返回时长（秒）
     * fee 费用
     * @param request
     * @throws IOException
     */
    private void  buildIndexMapping(CreateIndexRequest request) throws IOException {
        XContentBuilder mappingBuilder = JsonXContent.contentBuilder()
                .startObject()
                .startObject("properties")
                .startObject("mobile")
                .field("type", "keyword")
                .field("index", "true")
                .endObject()

                .startObject("createDate")
                .field("type", "date")
                .field("index", "true")
                .endObject()

                .endObject();

        request.mapping(mappingBuilder);
    }
}
