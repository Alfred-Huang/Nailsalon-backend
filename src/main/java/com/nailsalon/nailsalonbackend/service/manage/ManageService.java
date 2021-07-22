package com.nailsalon.nailsalonbackend.service.manage;

import com.nailsalon.nailsalonbackend.pojo.Employee;
import com.nailsalon.nailsalonbackend.pojo.Schedule;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ManageService {

    void addEmployee(List<Employee> employee);
    void addSchedule(Schedule schedule);
    List<Map<String, Object>> getEmployee() throws IOException;
    void deleteEmployee(String id);
    Map<String, Object>  getSchedule(String date) throws IOException;
    void updateSchedule(String date, String employee);
    void deleteSchedule(String date, String employee);

}
