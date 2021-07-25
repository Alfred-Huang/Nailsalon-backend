package com.nailsalon.nailsalonbackend.controller;

import com.nailsalon.nailsalonbackend.service.analytic.AnalyticService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/analytics")
public class AnalyticController {

    @Autowired
    AnalyticService analyticService;

    @PostMapping("/getMonthlySummary")
    public List<Map<String, String>> getMonthSummary(@RequestBody Map<String, String> map){
        List<Map<String, String>> result = new ArrayList<>();
        try {
            result =  analyticService.getMonthlySummary(map.get("curMonth"), map.get("nextMonth"));
        } catch (IOException e) {
            e.printStackTrace();
            return  result;
        }
        return result;
    }

    @PostMapping("/getDailySummary")
    public List<Map<String, String>> getDailySummary(@RequestBody Map<String, String> map){
        List<Map<String, String>> result = new ArrayList<>();
        try {
            result =  analyticService.getDailySummary(map.get("date"));
        } catch (IOException e) {
            e.printStackTrace();
            return  result;
        }
        return result;
    }

    @PostMapping("/getEmployeeSummary")
    public List<Map<String, String>> getEmployeeSummary(@RequestBody Map<String, String> map){
        List<Map<String, String>> result = new ArrayList<>();
        try {
            result =  analyticService.getEmployeeSummary(map.get("curMonth"), map.get("nextMonth"));
        } catch (IOException e) {
            e.printStackTrace();
            return  result;
        }
        return result;
    }

    @PostMapping("/getServiceSummary")
    public List<Map<String, String>> getServiceSummary(@RequestBody Map<String, String> map){
        List<Map<String, String>> result = new ArrayList<>();
        try {
            result =  analyticService.getServiceSummary(map.get("curMonth"), map.get("nextMonth"));
        } catch (IOException e) {
            e.printStackTrace();
            return  result;
        }
        return result;
    }

    @PostMapping("/getYearlySummary")
    public List<Map<String, String>> getYearlySummary(@RequestBody Map<String, String> map){
        List<Map<String, String>> result = new ArrayList<>();
        try {
            result =  analyticService.getYearlySummary(map.get("curYear"), map.get("nextYear"));
        } catch (IOException e) {
            e.printStackTrace();
            return  result;
        }
        return result;
    }
}
