package com.nailsalon.nailsalonbackend.mapper.sale;

import com.nailsalon.nailsalonbackend.pojo.Sale;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface SaleMapper {
    void addSaleRecord(@Param("saleId") String saleId, @Param("date") String date,
                       @Param("time" )String time ,@Param("totalPrice") Integer totalPrice,
                       @Param("employees")String employees, @Param("services") String services
                );

    void addServiceRecord(@Param("saleId") String saleId, @Param("date") String date,
                          @Param("time" )String time,  @Param("serviceList") List<Map<String, String>> serviceList);

    void addEmployeeSaleRecord(@Param("saleId") String saleId, @Param("date") String date,
                          @Param("time" )String time,   @Param("employeeList") List<Map<String, Object>> employeeList);
}
