package com.nailsalon.nailsalonbackend.service.product;

import com.alibaba.fastjson.JSONObject;
import com.nailsalon.nailsalonbackend.mapper.product.ProductMapper;
import com.nailsalon.nailsalonbackend.pojo.Product;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ProductServiceImpl implements ProductService{
    @Resource
    private RestHighLevelClient client;

    final
    ProductMapper productMapper;

    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public void addProduct(List<Product> products) throws IOException {
        productMapper.addProduct(products);
        for(Product product: products){
            String id = product.getProductId();
            IndexRequest request=new IndexRequest("product_table").id(id).source(beanToMap(product));
            client.index(request, RequestOptions.DEFAULT);
        }

    }

    @Override
    public void updateProduct(Product product) throws IOException {
        productMapper.updateProduct(product);
        String id = product.getProductId();
        UpdateRequest request = new UpdateRequest("product_table", id).doc(beanToMap(product));
        client.update(request, RequestOptions.DEFAULT);
    }


    @Override
    public List<Map<String, Object>> getProduct() throws IOException {
        String[] include = {"productId", "brand", "type", "quantity", "number"};
        String[] exclude = {"_index"};
        SearchRequest searchRequest = new SearchRequest("product_table");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder.fetchSource(include, exclude).sort("brand.keyword", SortOrder.ASC));
        SearchResponse response = client.search(searchRequest,RequestOptions.DEFAULT);
        List<Map<String, Object>> list = new ArrayList<>();
        for(SearchHit documentFields : response.getHits().getHits()){
            list.add(documentFields.getSourceAsMap());
        }
        return list;
    }

    @Override
    public List<String> getBrandTag() throws IOException {
        SearchRequest searchRequest = new SearchRequest("product_table");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery());
        TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("brands")
                .field("brand.keyword");
        builder.aggregation(aggregationBuilder);
        searchRequest.source(builder.sort("brand.keyword", SortOrder.ASC));
        builder.size(0);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        Aggregations aggregations = searchResponse.getAggregations();
        Terms byShopAggregation = aggregations.get("brands");
        List<String> result = new ArrayList<>();
        for (Terms.Bucket bucket  : byShopAggregation.getBuckets()) {
            result.add((String)bucket.getKey());
        }
       return result;
    }

    @Override
    public List<Map<String, Object>> getProductByBrand(String targetBrand) throws IOException {
        String[] include = {"productId", "brand", "type", "quantity", "number"};
        String[] exclude = {"_index"};
        SearchRequest searchRequest = new SearchRequest("product_table");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("brand", targetBrand));
        searchRequest.source(searchSourceBuilder.fetchSource(include, exclude));
        SearchResponse response = client.search(searchRequest,RequestOptions.DEFAULT);
        List<Map<String, Object>> list = new ArrayList<>();
        for(SearchHit documentFields : response.getHits().getHits()){
            list.add(documentFields.getSourceAsMap());
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> getProductByQuantity(String targetQuantity) throws IOException {
        String[] include = {"productId", "brand", "type", "quantity", "number"};
        String[] exclude = {"_index"};
        SearchRequest searchRequest = new SearchRequest("product_table");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("quantity", targetQuantity));
        searchRequest.source(searchSourceBuilder.fetchSource(include, exclude));
        SearchResponse response = client.search(searchRequest,RequestOptions.DEFAULT);
        List<Map<String, Object>> list = new ArrayList<>();
        for(SearchHit documentFields : response.getHits().getHits()){
            list.add(documentFields.getSourceAsMap());
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> getProductByType(String targetType) throws IOException {
        String[] include = {"productId", "brand", "type", "quantity", "number"};
        String[] exclude = {"_index"};
        SearchRequest searchRequest = new SearchRequest("product_table");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("type", targetType));
        searchRequest.source(searchSourceBuilder.fetchSource(include, exclude));
        SearchResponse response = client.search(searchRequest,RequestOptions.DEFAULT);
        List<Map<String, Object>> list = new ArrayList<>();
        for(SearchHit documentFields : response.getHits().getHits()){
            list.add(documentFields.getSourceAsMap());
        }
        return list;
    }

    @Override
    public void deleteProduct(String productId) throws IOException {
        productMapper.deleteProduct(productId);
        DeleteRequest request = new DeleteRequest("product_table", productId);
        client.delete(request, RequestOptions.DEFAULT);
    }

    @Override
    public List<Map<String, Object>> getProductByTag(List<String> tags) throws IOException {
        List<String> lowerCaseList = new ArrayList<>();
        for(String tag : tags){
            lowerCaseList.add(tag.toLowerCase());
        }
        String[] include = {"productId", "brand", "type", "quantity", "number"};
        String[] exclude = {"_index"};
        SearchRequest searchRequest = new SearchRequest("product_table");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termsQuery("brand", lowerCaseList));
        searchRequest.source(searchSourceBuilder.fetchSource(include, exclude));
        SearchResponse response = client.search(searchRequest,RequestOptions.DEFAULT);
        List<Map<String, Object>> result = new ArrayList<>();
        for(SearchHit documentFields : response.getHits().getHits()){
            result.add(documentFields.getSourceAsMap());
        }
        return result;
    }

    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String,Object> map=new HashMap<>();
        if (bean!=null){
            BeanMap beanMap= BeanMap.create(bean);
            for(Object key:beanMap.keySet()){
                if (beanMap.get(key)!=null){
                    map.put(key+"",beanMap.get(key));
                }
            }
        }
        return map;
    }
}
