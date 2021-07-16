package com.nailsalon.nailsalonbackend.service.appointment;

import com.nailsalon.nailsalonbackend.mapper.appointment.AppointmentMapper;
import com.nailsalon.nailsalonbackend.pojo.Appointment;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    @Autowired
    AppointmentMapper appointmentMapper;

    @Override
    public void addAppointment(Appointment appointment) {
        appointmentMapper.addAppointment(appointment);
    }
}
