package com.nailsalon.nailsalonbackend.mapper.manage;

import com.nailsalon.nailsalonbackend.pojo.Employee;
import com.nailsalon.nailsalonbackend.pojo.Schedule;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ManageMapper {
    void addEmployee(List<Employee> employees);
    void addScheduleMapper(Schedule schedule);
}
