package com.liz.light.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liz
 * @Description:
 * @Date: 2019/7/25 17:51
 * @Modified By:
 */
@Configuration
@Import(EsConfig.class)
public class ElasticSearchConfig {

    @Resource
    private EsConfig esConfig;

    @Bean
    public HttpHost[] httpHost(){
        String[] hosts = esConfig.getHost().split(",");
        List<HttpHost> list = new ArrayList();
        for (String s : hosts) {
            list.add(new HttpHost(s, esConfig.getPort(), esConfig.getScheme()));
        }
        return list.toArray(new HttpHost[list.size()]);
    }

    @Bean
    public RestHighLevelClient restHighLevelClient(){
        return new RestHighLevelClient(restClientBuilder());
    }

    @Bean
    public RestClientBuilder restClientBuilder(){
        return RestClient.builder(httpHost()).setRequestConfigCallback(builder -> {
            builder.setConnectTimeout(esConfig.getConnectTimeout());
            builder.setSocketTimeout(esConfig.getSocketTimeout());
            builder.setConnectionRequestTimeout(esConfig.getConnectionRequestTimeout());
            return builder;
        }).setHttpClientConfigCallback(httpAsyncClientBuilder -> {
            httpAsyncClientBuilder.setMaxConnTotal(esConfig.getMaxConnTotal());
            httpAsyncClientBuilder.setMaxConnPerRoute(esConfig.getMaxConnPerRoute());
            return httpAsyncClientBuilder;
        });
    }
}
