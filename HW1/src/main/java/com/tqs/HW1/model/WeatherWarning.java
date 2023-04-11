package com.tqs.HW1.model;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class WeatherWarning {

    private String district;
    private String warningType;
    private String level;

    private String text;


    public String getDistrict() {
        if(this.district == null) {
            return "";
        }
        return district;
    }

    public String getWarningType() {
        return warningType;
    }

    public String getLevel() {
        return level;
    }


    public void setDistrict(String getDistrictId) {
        this.district = getDistrictId;
    }

    public void setWarningType(String warningType) {
        this.warningType = warningType;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setText(String text) {
        this.text = text;
    }

}
