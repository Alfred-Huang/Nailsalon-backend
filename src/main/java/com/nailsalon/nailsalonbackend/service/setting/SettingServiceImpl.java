package com.nailsalon.nailsalonbackend.service.setting;

import com.nailsalon.nailsalonbackend.mapper.setting.SettingMapper;
import com.nailsalon.nailsalonbackend.pojo.Service;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public class SettingServiceImpl implements SettingService{
    @Resource
    private RestHighLevelClient client;

    final
    SettingMapper settingMapper;

    public SettingServiceImpl(SettingMapper settingMapper) {
        this.settingMapper = settingMapper;
    }

    @Override
    public void addService(Service service) {
        settingMapper.addService(service);
    }

    @Override
    public List<Map<String, Object>> getServiceList() throws IOException {
        String[] include = {"service_id", "service", "price"};
        String[] exclude = {"modification_time", "type", "deleted", "_index"};
        SearchRequest searchRequest = new SearchRequest("service_table");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query();
        BoolQueryBuilder qb =  QueryBuilders.boolQuery();
        qb.filter(QueryBuilders.termQuery("deleted", 1));
        searchSourceBuilder.query(qb);
        searchRequest.source(searchSourceBuilder.fetchSource(include, exclude));
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        List<Map<String, Object>> list = new ArrayList<>();
        for(SearchHit documentFields : response.getHits().getHits()){
            list.add(documentFields.getSourceAsMap());
        }
        return list;
    }

    @Override
    public void updateService(Service service) {
        settingMapper.updateService(service);
    }

    @Override
    public void deleteService(String id) {
        settingMapper.deleteService(id);
    }


}
