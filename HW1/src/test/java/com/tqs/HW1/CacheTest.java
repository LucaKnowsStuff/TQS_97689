package com.tqs.HW1;

import com.tqs.HW1.cache.Cache;
import com.tqs.HW1.model.WeatherWarning;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

public class CacheTest {

    private Cache cache;

    private WeatherWarning warning1;
    private WeatherWarning warning2;


    @BeforeEach
    void setUp() {
        this.cache = new Cache();
        this.warning1 = new WeatherWarning();
        this.warning2 = new WeatherWarning();
        warning1.setDistrict("Aveiro");
        warning1.setLevel("yellow");
        warning1.setWarningType("Nevoeiro");
        warning2.setDistrict("Viseu");
        warning2.setLevel("red");
        warning2.setWarningType("Chuva");
    }

    @AfterEach
    void tearDown() {
        cache.clearCache();
    }

    @Test
    void cacheSizeAddClearTest() {
        assertFalse(cache.containsWarning(warning1));
        assertEquals(0, cache.getCacheSize());
        cache.addWarning(warning1);
        assertEquals(1 , cache.getCacheSize());
        cache.addWarning(warning2);
        assertEquals(2, cache.getCacheSize());
        assertTrue(cache.containsWarning(warning2));
        cache.clearCache();
        assertEquals(0 ,cache.getCacheSize());
    }
    @Test
    void cacheHitMissRequestGetTest() {
        cache.addWarning(warning1);
        WeatherWarning warning3 = this.cache.getWarning("Aveiro" , "Nevoeiro");
        assertEquals(1 , this.cache.getHits());
        assertEquals(1 , this.cache.getRequests());
        assertEquals(0 , this.cache.getMisses());
        WeatherWarning warning4 = this.cache.getWarning("Lisboa" , "Chuva");
        WeatherWarning warning5 = this.cache.getWarning("Braga" , "Trovoada");
        cache.addWarning(warning2);
        WeatherWarning warning6 = this.cache.getWarning("Viseu" , "Chuva");
        assertEquals(2, this.cache.getHits());
        assertEquals(4, this.cache.getRequests());
        assertEquals(2, this.cache.getMisses());
        assertNotEquals(null, warning6);
        assertNull(warning5);
    }




}
