package com.nailsalon.nailsalonbackend.mapper.product;

import com.nailsalon.nailsalonbackend.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProductMapper {
    void addProduct(List<Product> product);
}
