package com.nailsalon.nailsalonbackend.mapper.appointment;

import com.nailsalon.nailsalonbackend.pojo.Appointment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AppointmentMapper {
    void addAppointment(Appointment appointment);
}
