package com.nailsalon.nailsalonbackend.mapper.manage;

import com.nailsalon.nailsalonbackend.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface EmployeeMapper {

    void addEmployee(Employee employee);
}
