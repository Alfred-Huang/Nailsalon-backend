package com.nailsalon.nailsalonbackend;


import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.ml.job.results.Bucket;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.*;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.histogram.ParsedDateHistogram;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ParsedSum;
import org.elasticsearch.search.aggregations.metrics.SumAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

@SpringBootTest
class NailsalonbackendApplicationTests {

    @Resource
    private RestHighLevelClient client;


    @Test
    public void getService() throws IOException {
            String[] include = {"service_id", "service", "price"};
            String[] exclude = {"modification_time", "type", "deleted", "_index"};
            SearchRequest searchRequest = new SearchRequest("service_table");
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query();
            searchRequest.source(searchSourceBuilder.fetchSource(include, exclude));
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            List<Map<String, Object>> list = new ArrayList<>();
            for(SearchHit documentFields : response.getHits().getHits()){
                list.add(documentFields.getSourceAsMap());
            }
            System.out.println(list);
    }

    @Test
    public void getSchedule() throws IOException {
        String[] include = {"employee"};
        String[] exclude = {"modification_time", "type", "deleted"};
        SearchRequest searchRequest = new SearchRequest("employee_schedule_table");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("date", "2021-07-01");
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
        System.out.println(a);
    }

    @Test
    public void getProduct() throws IOException {
        String[] include = {"brand", "type", "quantity", "number"};
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
        System.out.println(list);
    }


    @Test
    public void getD() throws IOException {
        SearchRequest searchRequest = new SearchRequest("service_record_table");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder query = QueryBuilders.boolQuery();
        query.must(QueryBuilders.rangeQuery("date").from("2021-07").to("2021-08").format("yyyy-MM"));
        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("service").field("service.keyword");
        searchSourceBuilder.query(query).aggregation(termsAggregationBuilder);
        searchSourceBuilder.size(0);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        Aggregations aggregations = searchResponse.getAggregations();
        ParsedStringTerms parsedDateHistogram = aggregations.get("service");
        List<Map<String, String>> result = new ArrayList<>();
        for (Terms.Bucket bucket  : parsedDateHistogram.getBuckets()) {
            Map<String, String> data = new HashMap<>();
            data.put("service", bucket.getKeyAsString());
            data.put("count", String.valueOf(bucket.getDocCount()));
            result.add(data);
        }
        System.out.println(result);
    }

}
