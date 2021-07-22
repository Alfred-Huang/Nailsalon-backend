package com.nailsalon.nailsalonbackend.controller;

import com.nailsalon.nailsalonbackend.pojo.Sale;
import com.nailsalon.nailsalonbackend.service.sale.SaleServiceImpl;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sale")
public class SaleController {

    final
    SaleServiceImpl saleService;

    public SaleController(SaleServiceImpl saleService) {
        this.saleService = saleService;
    }

    @PostMapping("/addSaleRecord")
    public void addSaleRecord(@RequestBody Map<String, Sale> map){
        saleService.addSaleRecord(map.get("saleRecord"));
    }

    @GetMapping("/getSaleRecord")
    public List<Map<String, Object>> getSaleRecord(@RequestParam("date") String date) {
        List<Map<String, Object>> result = new ArrayList<>();
        try {
           result = saleService.getSaleRecord(date);
        }catch(IOException e){
            e.printStackTrace();
            return result;
        }
        return result;
    }
}
