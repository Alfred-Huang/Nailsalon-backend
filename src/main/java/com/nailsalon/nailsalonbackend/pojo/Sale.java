package com.nailsalon.nailsalonbackend.pojo;

import java.util.Arrays;
import java.util.List;

public class Sale {
    private String saleId;
    private List<String> services;
    private List<String> employees;
    private List<String> employeeIdList;
    private List<String> serviceIdList;
    private List<Integer> priceList;
    private Integer totalPrice;
    private String date;
    private String time;

    public Sale(){}

    public Sale(String saleId, List<String> services, List<String> employees, List<String> employeeIdList, List<String> serviceIdList, List<Integer> priceList, Integer totalPrice, String date, String time) {
        this.saleId = saleId;
        this.services = services;
        this.employees = employees;
        this.employeeIdList = employeeIdList;
        this.serviceIdList = serviceIdList;
        this.priceList = priceList;
        this.totalPrice = totalPrice;
        this.date = date;
        this.time = time;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }

    public List<String> getEmployees() {
        return employees;
    }

    public void setEmployees(List<String> employees) {
        this.employees = employees;
    }

    public List<String> getEmployeeIdList() {
        return employeeIdList;
    }

    public void setEmployeeIdList(List<String> employeeIdList) {
        this.employeeIdList = employeeIdList;
    }

    public List<String> getServiceIdList() {
        return serviceIdList;
    }

    public void setServiceIdList(List<String> serviceIdList) {
        this.serviceIdList = serviceIdList;
    }

    public List<Integer> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<Integer> priceList) {
        this.priceList = priceList;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
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
        return "Sale{" +
                "saleId='" + saleId + '\'' +
                ", services=" + services +
                ", employees=" + employees +
                ", employeeIdList=" + employeeIdList +
                ", serviceIdList=" + serviceIdList +
                ", priceList=" + priceList +
                ", totalPrice=" + totalPrice +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
