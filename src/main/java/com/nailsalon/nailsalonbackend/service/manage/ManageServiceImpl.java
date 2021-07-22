package com.nailsalon.nailsalonbackend.service.manage;

import com.nailsalon.nailsalonbackend.mapper.manage.ManageMapper;
import com.nailsalon.nailsalonbackend.pojo.Employee;
import com.nailsalon.nailsalonbackend.pojo.Schedule;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManageServiceImpl implements ManageService {
    @Resource
    private RestHighLevelClient client;
    final
    ManageMapper manageMapper;

    public ManageServiceImpl(ManageMapper manageMapper) {
        this.manageMapper = manageMapper;
    }

    @Override
    public void addEmployee(List<Employee> employees) {
        manageMapper.addEmployee(employees);
    }

    @Override
    public void addSchedule(Schedule schedule) {
        manageMapper.addSchedule(schedule);
    }


    @Override
    public List<Map<String, Object>> getEmployee() throws IOException {
        String[] include = {"employee_id", "employee_name"};
        String[] exclude = {"modification_time", "type", "deleted"};
        SearchRequest searchRequest = new SearchRequest("employee_table");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(matchAllQueryBuilder);
        boolQueryBuilder.filter(QueryBuilders.termQuery("deleted", false));
        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder.fetchSource(include, exclude));
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        List<Map<String, Object>> list = new ArrayList<>();
        for(SearchHit documentFields : response.getHits().getHits()){
            list.add(documentFields.getSourceAsMap());
        }
        return list;
    }

    @Override
    public void deleteEmployee(String id) {
        manageMapper.deleteEmployee(id);
    }

    @Override
    public  Map<String, Object>  getSchedule(String date) throws IOException {
        String[] include = {"employee"};
        String[] exclude = {"modification_time", "type", "deleted"};
        SearchRequest searchRequest = new SearchRequest("employee_schedule_table");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("date", date);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(matchQuery);
        boolQueryBuilder.filter(QueryBuilders.termQuery("deleted", false));
        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder.fetchSource(include, exclude));
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        Map<String, Object> a = new HashMap<>();
        for(SearchHit documentFields : response.getHits().getHits()){
            a = (documentFields.getSourceAsMap());
        }
        return a;
    }

    @Override
    public void updateSchedule(String date, String employee) {
        manageMapper.updateSchedule(date, employee);
    }

    @Override
    public void deleteSchedule(String date, String employee) {
        manageMapper.deleteSchedule(date, employee);
    }
}
