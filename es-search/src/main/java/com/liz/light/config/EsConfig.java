package com.liz.light.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author liz
 * @Description:
 * @date: 2020/5/29 16:42
 */
@ConfigurationProperties(prefix = "es")
@Data
public class EsConfig {

    private String host;
    private Integer port;
    private String scheme;
    private Integer connectTimeout;
    private Integer socketTimeout;
    private Integer connectionRequestTimeout;
    private Integer maxConnTotal;
    private Integer maxConnPerRoute;
}
