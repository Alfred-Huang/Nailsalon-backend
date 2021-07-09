package com.nailsalon.nailsalonbackend.service;

import com.alibaba.fastjson.JSONObject;
import com.nailsalon.nailsalonbackend.pojo.NBAPlayer;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Service
public class NBAPlayerServiceImpl implements NBAPlayerService{
    @Resource
    private RestHighLevelClient client;
    private static final String NBA_INDEX = "nba_latest";

    @Override
    public boolean addPlayer(NBAPlayer player, String id) throws IOException {
        IndexRequest request=new IndexRequest("nba_latest").id(id).source(beanToMap(player));
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        System.out.println(JSONObject.toJSON(response));
        return false;
    }

    @Override
    public Map<String, Object> getPlayer(String id) throws IOException {
        GetRequest getRequest = new GetRequest(NBA_INDEX, id);
        GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
        return response.getSource();
    }

    @Override
    public boolean updatePlayer(NBAPlayer player, String id) throws IOException {
        UpdateRequest request = new UpdateRequest(NBA_INDEX, id).doc(beanToMap(player));
        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        System.out.println(JSONObject.toJSON(response));
        return true;
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
