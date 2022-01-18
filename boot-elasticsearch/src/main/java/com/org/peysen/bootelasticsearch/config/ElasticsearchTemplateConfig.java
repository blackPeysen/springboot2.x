package com.org.peysen.bootelasticsearch.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.net.InetAddress;

/**
 * Author: peimengmeng
 * Date: 2022/1/18 16:40
 * Desc:
 */
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "elasticsearch")
@Data
public class ElasticsearchTemplateConfig {

    private String clusterName;
    private String clusterNodes;
    private String clusterPassword;

    @Bean
    public Client client() throws Exception {
        Settings esSettings = Settings.builder()
                .put("cluster.name", clusterName)
                .put("xpack.security.user", clusterPassword)
                .put("xpack.security.transport.ssl.enabled", false)
                //增加嗅探机制，找到ES集群,非集群置为false
                .put("client.transport.sniff", false)
                //增加线程池个数
                .put("thread_pool.search.size", 20)
                .build();
        return new PreBuiltXPackTransportClient(esSettings).addTransportAddress(new TransportAddress(InetAddress.getByName(clusterNodes), 9300));
    }

    @Bean(name = "elasticsearchTemplate")
    public ElasticsearchTemplate elasticsearchTemplate() throws Exception {
        ElasticsearchTemplate elasticsearchTemplate;
        try {
            elasticsearchTemplate = new ElasticsearchTemplate(client());
            log.info("初始化ElasticsearchTemplate成功");
            return elasticsearchTemplate;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("初始化ElasticsearchTemplate失败");
            return new ElasticsearchTemplate(client());
        }
    }

}
