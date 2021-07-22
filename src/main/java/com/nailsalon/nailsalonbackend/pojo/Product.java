package com.nailsalon.nailsalonbackend.pojo;


import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "product_table")
public class Product {
    private String productId;
    private String brand;
    private String type;
    private String number;
    private Integer quantity;

    public Product(){}

    public Product(String productId, String brand, String type, String number, Integer quantity) {
        this.productId = productId;
        this.brand = brand;
        this.type = type;
        this.number = number;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", brand='" + brand + '\'' +
                ", type='" + type + '\'' +
                ", number='" + number + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
