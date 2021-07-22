package com.nailsalon.nailsalonbackend.controller;

import com.nailsalon.nailsalonbackend.pojo.Appointment;
import com.nailsalon.nailsalonbackend.service.appointment.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    @PostMapping("/getAppointment")
    public List<Map<String, Object>> getAppointment(@RequestBody Map<String, String> dateMap){
        List<Map<String, Object>> result = new ArrayList<>();
        try {
           result =  appointmentService.getAppointment(dateMap.get("targetDate"));
        } catch (IOException e) {
            e.printStackTrace();
            return  result;
        }
        return result;
    }

    @PostMapping("/deleteAppointment")
    public void deleteAppointment(@RequestBody Map<String, String> targetMap){
       appointmentService.deletedAppointment(targetMap.get("targetId"));
    }
}
