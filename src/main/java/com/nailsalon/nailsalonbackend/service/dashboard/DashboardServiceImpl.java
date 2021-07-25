package com.nailsalon.nailsalonbackend.service.dashboard;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ParsedSum;
import org.elasticsearch.search.aggregations.metrics.SumAggregationBuilder;
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
public class DashboardServiceImpl implements DashboardService{

    @Resource
    private RestHighLevelClient client;

    @Override
    public List<Map<String, Object>> getDailyHourSummary(String date) throws IOException {
        String[] include = { "price","time", "insertion_time"};
        String[] exclude = {"modification_time", "type", "deleted", "_index"};
        SearchRequest searchRequest = new SearchRequest("sale_table");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("date", date)).sort("insertion_time", SortOrder.ASC);
        searchRequest.source(searchSourceBuilder.fetchSource(include, exclude));
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        List<Map<String, Object>> list = new ArrayList<>();
        for(SearchHit documentFields : response.getHits().getHits()){
            list.add(documentFields.getSourceAsMap());
        }
        return list;
    }

    @Override
    public List<Map<String, String>> getEmployeeSummary(String date) throws IOException {
        SearchRequest searchRequest = new SearchRequest("employee_sale_table");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("date", date));
        TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("employee")
                .field("employee.keyword");
        SumAggregationBuilder saleSumAgg = AggregationBuilders.sum("totalSale").field("sale");
        aggregationBuilder.subAggregation(saleSumAgg);
        searchSourceBuilder.aggregation(aggregationBuilder);
        searchRequest.source(searchSourceBuilder);
        searchSourceBuilder.size(0);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        Aggregations aggregations = searchResponse.getAggregations();
        Terms byShopAggregation = aggregations.get("employee");
        List<Map<String, String>> result = new ArrayList<>();
        for (Terms.Bucket bucket  : byShopAggregation.getBuckets()) {
            Map<String, String> data = new HashMap<>();
            data.put("employee", bucket.getKeyAsString());
            ParsedSum parsedSum = (ParsedSum) bucket.getAggregations().asMap().get("totalSale");
            data.put("sale", parsedSum.getValueAsString());
            result.add(data);
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> getAppointment(String date) throws IOException {
        String[] include = {"appointment_id", "customer","employee", "service", "date", "time", "people"};
        String[] exclude = {"_index", "modification_time", "insertion_time", "type"};
        SearchRequest searchRequest = new SearchRequest("appointment_table");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("date", date);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(matchQuery);
        boolQueryBuilder.filter(QueryBuilders.termQuery("deleted", false));
        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder.fetchSource(include, exclude).sort("time.keyword", SortOrder.ASC));
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        List<Map<String, Object>> list = new ArrayList<>();
        for(SearchHit documentFields : response.getHits().getHits()){
            list.add(documentFields.getSourceAsMap());
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> getProductReminder() throws IOException {
        String[] include = {"productId", "brand", "type", "quantity", "number"};
        String[] exclude = {"_index"};
        SearchRequest searchRequest = new SearchRequest("product_table");
        RangeQueryBuilder rangequerybuilder = QueryBuilders
                .rangeQuery("quantity")
                .from("1").to("2");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(rangequerybuilder);
        searchRequest.source(searchSourceBuilder.fetchSource(include, exclude).sort("brand.keyword", SortOrder.ASC));
        SearchResponse response = client.search(searchRequest,RequestOptions.DEFAULT);
        List<Map<String, Object>> list = new ArrayList<>();
        for(SearchHit documentFields : response.getHits().getHits()){
            list.add(documentFields.getSourceAsMap());
        }
        return list;
    }
}
