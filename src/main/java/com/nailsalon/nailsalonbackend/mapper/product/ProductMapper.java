package com.nailsalon.nailsalonbackend.mapper.product;

import com.nailsalon.nailsalonbackend.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ProductMapper {
    void addProduct(Product product);
}
