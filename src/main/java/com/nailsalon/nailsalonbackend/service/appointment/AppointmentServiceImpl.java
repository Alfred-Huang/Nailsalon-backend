package com.nailsalon.nailsalonbackend.service.appointment;

import com.nailsalon.nailsalonbackend.mapper.appointment.AppointmentMapper;
import com.nailsalon.nailsalonbackend.pojo.Appointment;
import org.apache.ibatis.annotations.Param;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AppointmentServiceImpl implements AppointmentService{
    @Resource
    private RestHighLevelClient client;
    final
    AppointmentMapper appointmentMapper;

    public AppointmentServiceImpl(AppointmentMapper appointmentMapper) {
        this.appointmentMapper = appointmentMapper;
    }

    @Override
    public void addAppointment(Appointment appointment) {
        appointmentMapper.addAppointment(appointment);
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
    public void deletedAppointment(String appointmentId) {
        appointmentMapper.deleteAppointment(appointmentId);
    }
}
