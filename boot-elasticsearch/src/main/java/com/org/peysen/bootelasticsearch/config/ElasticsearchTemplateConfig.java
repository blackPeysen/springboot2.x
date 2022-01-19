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
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.net.InetAddress;

/**
 * Author: peimengmeng
 * Date: 2022/1/18 16:40
 * Desc:
 */
@Data
@Slf4j
//@Configuration
//@ConfigurationProperties(prefix = "elasticsearch")
public class ElasticsearchTemplateConfig {

    private String clusterName;
    private String clusterNodes;
    private String clusterPassword;

//    @Bean
    public Client client() throws Exception {
        Settings esSettings = Settings.builder()
                .put("xpach.security.user", "")
                .put("xpack.security.user", "elastic:elastic")
                .put("xpack.security.enabled",true)
                .put("xpack.security.transport.ssl.enabled", true)
                .build();
        return new PreBuiltXPackTransportClient(esSettings).addTransportAddress(new TransportAddress(InetAddress.getByName(clusterNodes), 9300));
    }

//    @Bean(name = "elasticsearchTemplate")
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
