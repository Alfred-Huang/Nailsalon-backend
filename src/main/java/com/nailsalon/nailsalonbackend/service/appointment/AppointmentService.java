package com.nailsalon.nailsalonbackend.service.appointment;

import com.nailsalon.nailsalonbackend.pojo.Appointment;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface AppointmentService {
    void addAppointment(Appointment appointment);
    List<Map<String, Object>> getAppointment(String date) throws IOException;
    void deletedAppointment(String appointmentId);
}
