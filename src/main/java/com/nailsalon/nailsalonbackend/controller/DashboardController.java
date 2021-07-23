package com.nailsalon.nailsalonbackend.controller;

import com.nailsalon.nailsalonbackend.service.dashboard.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    DashboardService dashboardService;

    @PostMapping("/getSummary")
    public List<Map<String, Object>> getSummary(@RequestBody Map<String, String> map){
        List<Map<String, Object>> result = new ArrayList<>();
        try {
            result =  dashboardService.getDailyHourSummary(map.get("date"));
        } catch (IOException e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }

    @PostMapping("/getEmployeeSale")
    public List<Map<String, String>> getEmployeeSale(@RequestBody Map<String, String> map){
        List<Map<String, String>> result = new ArrayList<>();
        try {
            result =  dashboardService.getEmployeeSummary(map.get("date"));
        } catch (IOException e) {
            e.printStackTrace();
            return  result;
        }
        return result;
    }

    @PostMapping("/getAppointment")
    public List<Map<String, Object>> getAppointment(@RequestBody Map<String, String> map){
        List<Map<String, Object>> result = new ArrayList<>();
        try {
            result =  dashboardService.getAppointment(map.get("date"));
        } catch (IOException e) {
            e.printStackTrace();
            return  result;
        }
        return result;
    }

    @PostMapping("/getProduct")
    public List<Map<String, Object>> getProduct(){
        List<Map<String, Object>> result = new ArrayList<>();
        try {
            result =  dashboardService.getProductReminder();
        } catch (IOException e) {
            e.printStackTrace();
            return  result;
        }
        return result;
    }
}
