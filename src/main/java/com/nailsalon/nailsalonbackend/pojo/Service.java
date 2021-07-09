package com.nailsalon.nailsalonbackend.pojo;

public class Service {
    private String serviceId;
    private String service;
    private String price;

    public Service(){}

    public Service(String serviceId, String service, String price) {
        this.serviceId = serviceId;
        this.service = service;
        this.price = price;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Service{" +
                "serviceId='" + serviceId + '\'' +
                ", service='" + service + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
