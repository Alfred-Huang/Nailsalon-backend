package com.nailsalon.nailsalonbackend.service.analytic;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
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
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnalyticServiceImpl implements AnalyticService{

    @Resource
    private RestHighLevelClient client;

    @Override
    public List<Map<String, String>> getMonthlySummary(String curMonth, String nextMonth) throws IOException {
        SearchRequest searchRequest = new SearchRequest("sale_table");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder query = QueryBuilders.boolQuery();
        query.must(QueryBuilders.rangeQuery("date").from(curMonth).to(nextMonth).format("yyyy-MM"));
        DateHistogramAggregationBuilder monthlySaleAgg = AggregationBuilders
                .dateHistogram("date")
                .field("date")
                .calendarInterval(DateHistogramInterval.DAY)
                .format("yyyy-MM-dd");
        SumAggregationBuilder saleSumAgg = AggregationBuilders.sum("totalSale").field("price");
        monthlySaleAgg.subAggregation(saleSumAgg);
        searchSourceBuilder.query(query).aggregation(monthlySaleAgg);
        searchSourceBuilder.size(0);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        Aggregations aggregations = searchResponse.getAggregations();
        ParsedDateHistogram  parsedDateHistogram = aggregations.get("date");
        List<Map<String, String>> result = new ArrayList<>();
        for (Histogram.Bucket bucket  : parsedDateHistogram.getBuckets()) {
            Map<String, String> data = new HashMap<>();
            data.put("date", bucket.getKeyAsString());
            ParsedSum parsedSum = (ParsedSum) bucket.getAggregations().asMap().get("totalSale");
            data.put("sale", parsedSum.getValueAsString());
            result.add(data);
        }
        return result;
    }

    @Override
    public List<Map<String, String>> getDailySummary(String date) throws IOException {
        SearchRequest searchRequest = new SearchRequest("sale_table");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        DateHistogramAggregationBuilder monthlySaleAgg = AggregationBuilders
                .dateHistogram("hour")
                .field("time")
                .calendarInterval(DateHistogramInterval.HOUR)
                .format("HH:mm");
        SumAggregationBuilder saleSumAgg = AggregationBuilders.sum("totalSale").field("price");
        monthlySaleAgg.subAggregation(saleSumAgg);
        builder.query(QueryBuilders.termsQuery("date", date)).aggregation(monthlySaleAgg);
        builder.size(0);
        searchRequest.source(builder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        Aggregations aggregations = searchResponse.getAggregations();
        ParsedDateHistogram  parsedDateHistogram = aggregations.get("hour");
        List<Map<String, String>> result = new ArrayList<>();
        for (Histogram.Bucket bucket  : parsedDateHistogram.getBuckets()) {
            Map<String, String> data = new HashMap<>();
            data.put("hour", bucket.getKeyAsString());
            ParsedSum parsedSum = (ParsedSum) bucket.getAggregations().asMap().get("totalSale");
            data.put("sale", parsedSum.getValueAsString());
            result.add(data);
        }
        return result;
    }

    @Override
    public List<Map<String, String>> getYearlySummary(String curYear, String nextYear) throws IOException {
        SearchRequest searchRequest = new SearchRequest("sale_table");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder query = QueryBuilders.boolQuery();
        query.must(QueryBuilders.rangeQuery("date").from(curYear).to(nextYear).format("yyyy"));
        DateHistogramAggregationBuilder monthlySaleAgg = AggregationBuilders
                .dateHistogram("date")
                .field("date")
                .calendarInterval(DateHistogramInterval.MONTH)
                .format("yyyy-MM-dd");
        SumAggregationBuilder saleSumAgg = AggregationBuilders.sum("totalSale").field("price");
        monthlySaleAgg.subAggregation(saleSumAgg);
        searchSourceBuilder.query(query).aggregation(monthlySaleAgg);
        searchSourceBuilder.size(0);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        Aggregations aggregations = searchResponse.getAggregations();
        ParsedDateHistogram  parsedDateHistogram = aggregations.get("date");
        List<Map<String, String>> result = new ArrayList<>();
        for (Histogram.Bucket bucket  : parsedDateHistogram.getBuckets()) {
            Map<String, String> data = new HashMap<>();
            data.put("date", bucket.getKeyAsString());
            ParsedSum parsedSum = (ParsedSum) bucket.getAggregations().asMap().get("totalSale");
            data.put("sale", parsedSum.getValueAsString());
            result.add(data);
        }
        return result;
    }

    @Override
    public List<Map<String, String>> getProductSummary() {
        return null;
    }

    @Override
    public List<Map<String, String>> getServiceSummary(String curMonth, String nextMonth) throws IOException {
        SearchRequest searchRequest = new SearchRequest("service_record_table");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder query = QueryBuilders.boolQuery();
        query.must(QueryBuilders.rangeQuery("date").from(curMonth).to(nextMonth).format("yyyy-MM"));
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
        return result;

    }

    @Override
    public List<Map<String, String>> getEmployeeSummary(String curMonth, String nextMonth) throws IOException {
        SearchRequest searchRequest = new SearchRequest("employee_sale_table");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder query = QueryBuilders.boolQuery();
        query.must(QueryBuilders.rangeQuery("date").from(curMonth).to(nextMonth).format("yyyy-MM"));
        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("employee").field("employee.keyword");
        SumAggregationBuilder saleSumAgg = AggregationBuilders.sum("totalSale").field("sale");
        termsAggregationBuilder.subAggregation(saleSumAgg);
        searchSourceBuilder.query(query).aggregation(termsAggregationBuilder);
        searchSourceBuilder.size(0);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        Aggregations aggregations = searchResponse.getAggregations();
        ParsedStringTerms parsedDateHistogram = aggregations.get("employee");
        List<Map<String, String>> result = new ArrayList<>();
        for (Terms.Bucket bucket  : parsedDateHistogram.getBuckets()) {
            Map<String, String> data = new HashMap<>();
            data.put("employee", bucket.getKeyAsString());
            ParsedSum parsedSum = (ParsedSum) bucket.getAggregations().asMap().get("totalSale");
            data.put("sale", parsedSum.getValueAsString());
            result.add(data);
        }
       return result;
    }
}
