package com.nailsalon.nailsalonbackend;

import com.alibaba.fastjson.JSONObject;
import com.nailsalon.nailsalonbackend.dao.NBAPlayerDao;
import com.nailsalon.nailsalonbackend.pojo.NBAPlayer;
import com.nailsalon.nailsalonbackend.service.NBAPlayerService;
import org.elasticsearch.action.admin.indices.create.CreateIndexAction;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Map;

@SpringBootTest
class NailsalonbackendApplicationTests {

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

}
