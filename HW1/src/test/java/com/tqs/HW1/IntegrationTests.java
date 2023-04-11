package com.tqs.HW1;

import com.tqs.HW1.model.WeatherWarning;
import com.tqs.HW1.service.WarningService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class IntegrationTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private WarningService service;

    @Test
    void getAllWarningsTests() throws Exception {
        WeatherWarning warning = new WeatherWarning();
        warning.setWarningType("Chuva");
        warning.setDistrict("Aveiro");
        warning.setLevel("Orange");
        ArrayList<WeatherWarning> warnings = new ArrayList<>();
        warnings.add(warning);
        when(this.service.getAllWarnings()).thenReturn(warnings);
        mvc.perform(get("/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].district", is("Aveiro")))
                .andExpect(jsonPath("$[0].warningType", is("Chuva")))
                .andExpect(jsonPath("$[0].level", is("Orange")));


    }

    @Test
    void getByDistrictTest() throws Exception {
        WeatherWarning warning2 = new WeatherWarning();
        WeatherWarning warning3 = new WeatherWarning();
        warning2.setWarningType("Chuva");
        warning2.setDistrict("Guarda");
        warning2.setLevel("orange");
        warning3.setWarningType("Nevoeiro");
        warning3.setDistrict("Guarda");
        warning3.setLevel("red");
        ArrayList<WeatherWarning> warnings = new ArrayList<>();
        warnings.add(warning2);
        warnings.add(warning3);
        when(this.service.getDistrictWarnings("Guarda")).thenReturn(warnings);
        mvc.perform(get("/district/Guarda").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].district", is("Guarda")))
                .andExpect(jsonPath("$[1].district" , is("Guarda")))
                .andExpect(jsonPath("$[0].warningType" , is("Chuva")))
                .andExpect(jsonPath("$[1].warningType" , is("Nevoeiro")))
                .andExpect(jsonPath("$[0].level" , is("orange")))
                .andExpect(jsonPath("$[1].level" , is("red")));

    }

    @Test
    void getByLevelTest() throws Exception {
        WeatherWarning warning2 = new WeatherWarning();
        WeatherWarning warning3 = new WeatherWarning();
        warning2.setWarningType("Neve");
        warning2.setDistrict("Braga");
        warning2.setLevel("red");
        warning3.setWarningType("Tempo Frio");
        warning3.setDistrict("Faro");
        warning3.setLevel("red");
        ArrayList<WeatherWarning> warnings = new ArrayList<>();
        warnings.add(warning2);
        warnings.add(warning3);
        when(this.service.getWarningbyLevel("red")).thenReturn(warnings);
        mvc.perform(get("/level/red").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].district", is("Braga")))
                .andExpect(jsonPath("$[1].district" , is("Faro")))
                .andExpect(jsonPath("$[0].warningType" , is("Neve")))
                .andExpect(jsonPath("$[1].warningType" , is("Tempo Frio")))
                .andExpect(jsonPath("$[0].level" , is("red")))
                .andExpect(jsonPath("$[1].level" , is("red")));
    }

    @Test
    void getCacheInfoTest() throws Exception {
        HashMap<String, Integer> mockCache = new HashMap<>();
        mockCache.put("misses", 10);
        mockCache.put("hits", 5);
        mockCache.put("requests", 15);
        when(this.service.getCacheInfo()).thenReturn(mockCache);
        mvc.perform(get("/cache").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.misses", is (10)))
                .andExpect(jsonPath("$.hits", is (5)))
                .andExpect(jsonPath("$.requests", is (15)));

    }

}
