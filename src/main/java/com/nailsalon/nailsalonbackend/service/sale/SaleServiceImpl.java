package com.nailsalon.nailsalonbackend.service.sale;

import com.nailsalon.nailsalonbackend.mapper.sale.SaleMapper;
import com.nailsalon.nailsalonbackend.pojo.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleServiceImpl implements SaleService{

    final
    SaleMapper saleMapper;

    public SaleServiceImpl(SaleMapper saleMapper) {
        this.saleMapper = saleMapper;
    }

    @Override
    public void addSaleRecord(Sale saleRecord) {
        saleMapper.addSaleMapper(saleRecord);
    }
}
