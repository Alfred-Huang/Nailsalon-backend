package com.nailsalon.nailsalonbackend.service.manage;

import com.nailsalon.nailsalonbackend.pojo.Employee;
import com.nailsalon.nailsalonbackend.pojo.Schedule;

import java.util.List;

public interface ManageService {

    void addEmployee(List<Employee> employee);
    void addScheduleMapper(Schedule schedule);
}
