package com.nailsalon.nailsalonbackend.mapper.setting;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SettingMapper {

    void addService();
}
