package com.nailsalon.nailsalonbackend.mapper.sale;

import com.nailsalon.nailsalonbackend.pojo.Sale;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SaleMapper {
    void addSaleMapper(Sale saleRecord);
}
