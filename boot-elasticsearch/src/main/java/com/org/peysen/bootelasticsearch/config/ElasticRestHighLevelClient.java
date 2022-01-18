package com.org.peysen.bootelasticsearch.config;

import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: peimengmeng
 * Date: 2022/1/17 10:09
 * Desc:
 */
@Component
@Slf4j
public class ElasticRestHighLevelClient {

    @Autowired
    private EsProperties esProperties;
    private RestHighLevelClient client;

//    @PostConstruct
    public void init() {
        try {
            List<HttpHost> hosts = parseClusterNodes(esProperties.getProtocol(), esProperties.getClusterNodes());
            client = createClient(hosts, esProperties.getUser(), esProperties.getPassword(), esProperties.getTrustStorePath(), esProperties.getTrustStorePass());
        }
        catch (Exception e) {
            System.out.println("ES Client init failed." +  e);
        }
    }

    public static List<HttpHost> parseClusterNodes(String protocol, String nodes) {
        List<HttpHost> httpHosts = new LinkedList<>();
        List<String> nodesSplit = Splitter.on(",").omitEmptyStrings().splitToList(nodes);

        for (String node : nodesSplit) {
            String host = node.split(":")[0];
            String port = node.split(":")[1];
            httpHosts.add(new HttpHost(host, Integer.parseInt(port), protocol));
        }

        return httpHosts;
    }

    protected RestHighLevelClient createClient(final List<HttpHost> pairsList, String user, String password, String trustStorePath, String trustStorePass) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, KeyManagementException {
        RestClientBuilder builder;
        if (StringUtils.isNotEmpty(user) && StringUtils.isNotEmpty(password)) {
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(user, password));

            if (StringUtils.isEmpty(trustStorePath)) {
                builder = RestClient.builder(pairsList.toArray(new HttpHost[0]))
                        .setHttpClientConfigCallback(
                                httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(
                                        credentialsProvider));
            }
            else {
                KeyStore truststore = KeyStore.getInstance("jks");
                try (InputStream is = Files.newInputStream(Paths.get(trustStorePath))) {
                    truststore.load(is, trustStorePass.toCharArray());
                }
                SSLContextBuilder sslBuilder = SSLContexts.custom().loadTrustMaterial(truststore, null);
                final SSLContext sslContext = sslBuilder.build();
                builder = RestClient.builder(pairsList.toArray(new HttpHost[0]))
                        .setHttpClientConfigCallback(
                                httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(
                                        credentialsProvider)
                                        .setSSLContext(sslContext));
            }
        }
        else {
            builder = RestClient.builder(pairsList.toArray(new HttpHost[0]));
        }

        return new RestHighLevelClient(builder);
    }

}
