package com.nailsalon.nailsalonbackend.controller;

import com.nailsalon.nailsalonbackend.pojo.Employee;
import com.nailsalon.nailsalonbackend.pojo.Schedule;
import com.nailsalon.nailsalonbackend.service.manage.ManageServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/manage")
public class ManageController {



    final
    ManageServiceImpl manageService;

    public ManageController(ManageServiceImpl employeeService) {
        this.manageService = employeeService;
    }

    @PostMapping("/addSchedule")
    public void addSchedule(@RequestBody Map<String, Schedule> map){
        manageService.addSchedule(map.get("schedule"));
    }

    @PostMapping("/addEmployee")
    public void addEmployee(@RequestBody Map<String, List<Employee>> map){
        manageService.addEmployee(map.get("employeeList"));
    }

    @PostMapping("/getEmployee")
    public  List<Map<String, Object>> setEmployee(){
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            list = manageService.getEmployee();
        } catch (IOException e) {
            e.printStackTrace();
            return list;
        }
        return list;
    }

    @PostMapping("/deleteEmployee")
    public void deleteEmployee(@RequestBody Map<String, String> map){
        manageService.deleteEmployee(map.get("id"));
    }


    @PostMapping("/getSchedule")
    public List<String> getSchedule(@RequestBody Map<String, String> map){
        Map<String, Object> scheduleResult = new HashMap<>();
        try {
            scheduleResult = manageService.getSchedule(map.get("date"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> result = new ArrayList<>();
        if(!scheduleResult.containsKey("employee")){
            return result;
        }
        String string = (String) scheduleResult.get("employee");
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < string.length(); i++){
            if(string.charAt(i) != ' '){
                sb.append(string.charAt(i));
            }else{
                result.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        result.add(sb.toString());
        return result;
    }

    @PostMapping("updateSchedule")
    public void updateSchedule(@RequestBody Map<String, Map<String, String>> map){
        manageService.updateSchedule(map.get("data").get("date"), map.get("data").get("employee"));
    }

    @PostMapping("deleteSchedule")
    public void deleteSchedule(@RequestBody Map<String, Map<String, String>> map){
        manageService.updateSchedule(map.get("data").get("date"), map.get("data").get("employee"));
    }
}
