package com.nailsalon.nailsalonbackend.mapper.setting;

import com.nailsalon.nailsalonbackend.pojo.Service;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SettingMapper {

    void addService(Service service);

    void updateService(Service service);

    void deleteService(String id);
}
