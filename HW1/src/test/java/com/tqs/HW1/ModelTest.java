package com.tqs.HW1;

import com.tqs.HW1.model.WeatherWarning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelTest {

    private WeatherWarning warning1;

    @BeforeEach
    void setUp() {
        warning1 = new WeatherWarning();
    }

    @Test
    void modelTest() {
        warning1.setWarningType("Chuva");
        warning1.setDistrict("Braga");
        warning1.setLevel("Orange");
        assertEquals("Chuva" , this.warning1.getWarningType());
        assertEquals("Braga" , this.warning1.getDistrict());
        assertEquals("Orange" , this.warning1.getLevel());
    }



}