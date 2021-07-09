package com.nailsalon.nailsalonbackend.pojo;

import java.util.Arrays;

public class Sale {
    private String saleId;
    private String[] services;
    private String[] employees;
    private int[] priceList;
    private Integer totalPrice;
    private String date;
    private String time;

    public Sale(){}

    public Sale(String saleId, String[] services, String[] employees, int[] priceList, Integer totalPrice, String date, String time) {
        this.saleId = saleId;
        this.services = services;
        this.employees = employees;
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

    public String[] getServices() {
        return services;
    }

    public void setServices(String[] services) {
        this.services = services;
    }

    public String[] getEmployees() {
        return employees;
    }

    public void setEmployees(String[] employees) {
        this.employees = employees;
    }

    public int[] getPriceList() {
        return priceList;
    }

    public void setPriceList(int[] priceList) {
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
                "saleId=" + saleId +
                ", services=" + Arrays.toString(services) +
                ", employees=" + Arrays.toString(employees) +
                ", priceList=" + Arrays.toString(priceList) +
                ", totalPrice=" + totalPrice +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
