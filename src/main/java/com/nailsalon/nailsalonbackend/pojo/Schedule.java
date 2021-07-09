package com.nailsalon.nailsalonbackend.pojo;

public class Schedule {
    private String date;
    private String employee;

    public Schedule(String date, String employee) {
        this.date = date;
        this.employee = employee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "date='" + date + '\'' +
                ", employee='" + employee + '\'' +
                '}';
    }
}
