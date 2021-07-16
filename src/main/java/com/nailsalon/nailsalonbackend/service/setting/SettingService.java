package com.nailsalon.nailsalonbackend.service.setting;

import com.nailsalon.nailsalonbackend.pojo.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface SettingService {

    void addService(Service service);

    List<Map<String, Object>> getServiceList() throws IOException;

    void updateService(Service service);

    void deleteService(String id);
}
