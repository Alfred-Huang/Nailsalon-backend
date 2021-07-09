package com.nailsalon.nailsalonbackend.pojo;

import org.springframework.context.ApplicationContext;

public class Appointment {
    private String appointmentId;
    private String customer;
    private String service;
    private Integer people;
    private String employee;
    private String date;
    private String time;

    public Appointment(){};

    public Appointment(String appointmentId, String customer, String service, Integer people, String employee, String date, String time) {
        this.appointmentId = appointmentId;
        this.customer = customer;
        this.service = service;
        this.people = people;
        this.employee = employee;
        this.date = date;
        this.time = time;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId='" + appointmentId + '\'' +
                ", customer='" + customer + '\'' +
                ", service='" + service + '\'' +
                ", people=" + people +
                ", employee='" + employee + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
