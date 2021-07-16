package com.nailsalon.nailsalonbackend.controller;

import com.nailsalon.nailsalonbackend.pojo.Service;
import com.nailsalon.nailsalonbackend.service.setting.SettingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/setting")
public class SettingController {


    final
    SettingServiceImpl settingService;

    public SettingController(SettingServiceImpl settingService) {
        this.settingService = settingService;
    }

    @PostMapping("/addService")
    public void addService(@RequestBody Map<String, Service> map){
        settingService.addService(map.get("service"));
    }

    @PostMapping("/getServiceList")
    public List<Map<String, Object>> getServiceList(){
        List<Map<String, Object>> result;
        try {
            result = settingService.getServiceList();
        }catch (IOException ignored){
            return null;
        }
       return result;
    }

    @PostMapping("updateService")
    public void updateService(@RequestBody Map<String, Service> map){
        System.out.println(map.get("service"));
        settingService.updateService(map.get("service"));
    }

    @PostMapping("deleteService")
    public void updateService(@RequestBody String id){
        settingService.deleteService(id);
    }

}
