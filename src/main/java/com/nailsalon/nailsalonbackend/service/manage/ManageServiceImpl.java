package com.nailsalon.nailsalonbackend.service.manage;

import com.nailsalon.nailsalonbackend.mapper.manage.ManageMapper;
import com.nailsalon.nailsalonbackend.pojo.Employee;
import com.nailsalon.nailsalonbackend.pojo.Schedule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageServiceImpl implements ManageService {

    final
    ManageMapper manageMapper;

    public ManageServiceImpl(ManageMapper manageMapper) {
        this.manageMapper = manageMapper;
    }

    @Override
    public void addEmployee(List<Employee> employees) {
        manageMapper.addEmployee(employees);
    }

    @Override
    public void addScheduleMapper(Schedule schedule) {
        manageMapper.addScheduleMapper(schedule);
    }
}
