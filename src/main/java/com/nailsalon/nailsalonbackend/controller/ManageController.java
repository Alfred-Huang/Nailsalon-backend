package com.nailsalon.nailsalonbackend.controller;

import com.nailsalon.nailsalonbackend.pojo.Employee;
import com.nailsalon.nailsalonbackend.pojo.Schedule;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/manage")
public class ManageController {

    @PostMapping("/addSchedule")
    public void addSchedule(@RequestBody Map<String, Schedule> map){
        System.out.println(map.get("schedule"));
    }

    @PostMapping("/addEmployee")
    public void addEmployee(@RequestBody Map<String, List<Employee>> map){
        System.out.println(map.get("employeeList"));
    }
}
