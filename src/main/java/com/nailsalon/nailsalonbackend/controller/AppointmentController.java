package com.nailsalon.nailsalonbackend.controller;

import com.nailsalon.nailsalonbackend.pojo.Appointment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @PostMapping("/addAppointment")
    public void addAppointment(@RequestBody Map<String, Appointment> map){
        System.out.println(map.get("appointment"));
    }
}
