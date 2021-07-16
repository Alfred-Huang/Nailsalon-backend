package com.nailsalon.nailsalonbackend.service.sale;

import com.nailsalon.nailsalonbackend.pojo.Sale;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface SaleService {
    void addSaleRecord(Sale sale);
//    List<Map<String, Object>> getSaleRecord(String from, String to) throws IOException;
        List<Map<String, Object>> getSaleRecord(String date) throws IOException;

}
