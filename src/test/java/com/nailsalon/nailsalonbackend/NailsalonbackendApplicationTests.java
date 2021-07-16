package com.nailsalon.nailsalonbackend;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nailsalon.nailsalonbackend.dao.NBAPlayerDao;
import com.nailsalon.nailsalonbackend.pojo.NBAPlayer;
import com.nailsalon.nailsalonbackend.service.NBAPlayerService;
import org.elasticsearch.action.admin.indices.create.CreateIndexAction;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
class NailsalonbackendApplicationTests {

    @Resource
    private RestHighLevelClient client;

    @Autowired
    private NBAPlayerDao nbaPlayerDao;

    @Autowired
    private NBAPlayerService nbaPlayerService;


    @Test
    public void selectAll(){
        System.out.println(JSONObject.toJSON(nbaPlayerDao.selectAll()));
    }

    @Test
    public void addPlayer() throws IOException {
        NBAPlayer nbaPlayer = new NBAPlayer();
        nbaPlayer.setId(999);
        nbaPlayer.setDisplayName("caonima");
        nbaPlayerService.addPlayer(nbaPlayer, "999");
    }

    @Test
    public void update() throws IOException {
        NBAPlayer nbaPlayer = new NBAPlayer();
        nbaPlayer.setId(000);
        nbaPlayer.setDisplayName("shabi");
        nbaPlayerService.updatePlayer(nbaPlayer, "999");
    }

    @Test
    public void getPlayer() throws IOException {
        Map<String, Object> player = nbaPlayerService.getPlayer("999");
        System.out.println(JSONObject.toJSON(player));
    }

    @Test
    public void getSale() throws IOException {
        String[] include = {"sale_id", "price", "time", "date","insertion_time"};
        String[] exclude = {"modification_time", "type", "deleted", "_index"};
        SearchRequest searchRequest = new SearchRequest("sale_table");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query( QueryBuilders.rangeQuery("date").format("yyyy/MM/dd").gte("2020/12/30").lte("2021/08/30")).sort("insertion_time", SortOrder.DESC);
        searchRequest.source(searchSourceBuilder.fetchSource(include, exclude));
        SearchResponse response = client.search(searchRequest,RequestOptions.DEFAULT);
        List<Map<String, Object>> list = new ArrayList<>();
        for(SearchHit documentFields : response.getHits().getHits()){
            list.add(documentFields.getSourceAsMap());
        }
        System.out.println(list);

    }

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

}
