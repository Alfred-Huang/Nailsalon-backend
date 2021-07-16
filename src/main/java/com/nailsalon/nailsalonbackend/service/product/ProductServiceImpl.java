package com.nailsalon.nailsalonbackend.service.product;

import com.nailsalon.nailsalonbackend.mapper.product.ProductMapper;
import com.nailsalon.nailsalonbackend.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService{


    final
    ProductMapper productMapper;

    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public void addProduct(List<Product> product) {
        productMapper.addProduct(product);
    }
}
