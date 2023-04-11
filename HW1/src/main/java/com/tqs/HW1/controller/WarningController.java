package com.tqs.HW1.controller;

import com.tqs.HW1.model.WeatherWarning;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tqs.HW1.service.WarningService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class WarningController {
    @Autowired
    public WarningService service;

    @GetMapping("/all")
    public ArrayList<WeatherWarning> getAllWarnings() throws IOException, InterruptedException {
        return service.getAllWarnings();
    }

    @GetMapping("district/{district}")
    public ArrayList<WeatherWarning> getDistrictWarnings(@PathVariable(value = "district") String district) throws IOException, InterruptedException {
        return service.getDistrictWarnings(district);
    }

    @GetMapping("level/{level}")
    public ArrayList<WeatherWarning> getWarningbyLevel(@PathVariable(value = "level") String level) throws IOException, InterruptedException {
        return service.getWarningbyLevel(level);
    }


    @GetMapping("type/{type}")
    public ArrayList<WeatherWarning> getWarningbyType(@PathVariable(value = "type") String type) throws IOException, InterruptedException {
        return service.getWarningbyType(type);
    }

    @GetMapping("/cache")
    public HashMap<String, Integer> getCacheInfo() {
        return service.getCacheInfo();
    }
}
