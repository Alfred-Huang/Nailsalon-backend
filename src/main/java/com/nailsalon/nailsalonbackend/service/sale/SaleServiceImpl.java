package com.nailsalon.nailsalonbackend.service.sale;

import com.nailsalon.nailsalonbackend.mapper.sale.SaleMapper;
import com.nailsalon.nailsalonbackend.pojo.Sale;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SaleServiceImpl implements SaleService{

    @Resource
    private RestHighLevelClient client;

    final
    SaleMapper saleMapper;

    public SaleServiceImpl(SaleMapper saleMapper) {
        this.saleMapper = saleMapper;
    }

    @Override
    public void addSaleRecord(Sale saleRecord) {
        List<Map<String, Object>> employeeList = new ArrayList<>();
        List<Map<String, String>> serviceList = new ArrayList<>();
        StringBuilder employees = new StringBuilder();
        StringBuilder services = new StringBuilder();
        for(int i = 0; i < saleRecord.getEmployees().size(); i++){
            employees.append(saleRecord.getEmployees().get(i));
            if(i != saleRecord.getEmployees().size()){
                employees.append(" ");
            }
            Map<String, Object> map = new HashMap<>();
            map.put("employee", saleRecord.getEmployees().get(i));
            map.put("price", saleRecord.getPriceList().get(i));
            map.put("employeeId", saleRecord.getEmployeeIdList().get(i));
            employeeList.add(map);
        }

        for(int i = 0; i < saleRecord.getServices().size(); i++){
            services.append(saleRecord.getServices().get(i));
            if(i != saleRecord.getServices().size()){
                services.append(" ");
            }
            Map<String, String> map = new HashMap<>();
            map.put("service", saleRecord.getServices().get(i));
            map.put("serviceId", saleRecord.getServiceIdList().get(i));
            serviceList.add(map);
        }
        saleMapper.addSaleRecord(saleRecord.getSaleId(), saleRecord.getDate(), saleRecord.getTime(), saleRecord.getTotalPrice(), employees.toString(), services.toString());
        saleMapper.addEmployeeSaleRecord(saleRecord.getSaleId(), saleRecord.getDate(), saleRecord.getTime(), employeeList);
        saleMapper.addServiceRecord(saleRecord.getSaleId(), saleRecord.getDate(), saleRecord.getTime(), serviceList);
    }

    @Override
    public List<Map<String, Object>> getSaleRecord(String date) throws IOException {
        String[] include = {"sale_id", "price", "employees", "services", "time", "date","insertion_time"};
        String[] exclude = {"modification_time", "type", "deleted", "_index"};
        SearchRequest searchRequest = new SearchRequest("sale_table");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(  QueryBuilders.matchQuery("date", date)).sort("insertion_time", SortOrder.DESC);
        searchRequest.source(searchSourceBuilder.fetchSource(include, exclude));
        SearchResponse response = client.search(searchRequest,RequestOptions.DEFAULT);
        List<Map<String, Object>> list = new ArrayList<>();
        for(SearchHit documentFields : response.getHits().getHits()){
            list.add(documentFields.getSourceAsMap());
        }
        return list;
    }



}
