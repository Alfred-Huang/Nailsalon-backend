package com.nailsalon.nailsalonbackend.mapper.manage;

import com.nailsalon.nailsalonbackend.pojo.Employee;
import com.nailsalon.nailsalonbackend.pojo.Schedule;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ManageMapper {
    void addEmployee(List<Employee> employees);
    void deleteEmployee(String id);
    void addSchedule(Schedule schedule);
    void updateSchedule(String date, String employee);
    void deleteSchedule(String date, String employee);
}
