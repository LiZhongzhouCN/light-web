package com.liz.light.service;

import com.liz.light.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author liz
 * @Description:
 * @date: 2020/5/29 14:58
 */
@Slf4j
@Component
public class FaceIndexSearchService {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    public long getCount(){
        SearchRequest searchRequest = new SearchRequest().indices("security_web-2020.05.21");
        try {
            SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            return response.getHits().getTotalHits().value;
        } catch (IOException e) {
            e.printStackTrace();
            throw new CommonException(CommonException.ExceptionCode.INTERFACE_CALL_FAIL);
        }
    }

}
