package com.nailsalon.nailsalonbackend.service.product;

import com.nailsalon.nailsalonbackend.pojo.Product;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ProductService {
    void addProduct(List<Product> product) throws IOException;
    void updateProduct(Product product) throws IOException;
    List<Map<String, Object>> getProduct() throws IOException;
    List<String> getBrandTag() throws IOException;
    List<Map<String, Object>> getProductByBrand(String targetBrand) throws IOException;
    List<Map<String, Object>> getProductByQuantity(String targetQuantity) throws IOException;
    List<Map<String, Object>> getProductByType(String targetType) throws IOException;
    void deleteProduct(String productId) throws IOException;
    List<Map<String, Object>> getProductByTag(List<String> tags) throws IOException;
}
