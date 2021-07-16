package com.nailsalon.nailsalonbackend.controller;

import com.nailsalon.nailsalonbackend.pojo.Appointment;
import com.nailsalon.nailsalonbackend.service.appointment.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    final
    AppointmentServiceImpl appointmentService;

    public AppointmentController(AppointmentServiceImpl appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/addAppointment")
    public void addAppointment(@RequestBody Map<String, Appointment> map){
      appointmentService.addAppointment(map.get("appointment"));
    }
}
