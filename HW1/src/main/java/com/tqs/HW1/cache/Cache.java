package com.tqs.HW1.cache;

import com.tqs.HW1.model.WeatherWarning;

import java.util.ArrayList;

public class Cache {

    public ArrayList<WeatherWarning> cache = new ArrayList<>();
    private int hits = 0;

    private int misses = 0;


    public int getMisses() {
        return this.misses;
    }

    public int getHits() {
        return this.hits;
    }

    public void addHit() {
        this.hits++;
    }

    public void addMiss() {
        this.misses++;
    }
    public int getRequests() {
        return this.misses + this.hits;
    }

    public ArrayList<WeatherWarning> getCache() {
        return this.cache;
    }

    public boolean containsWarning(WeatherWarning warning) {
        return this.cache.contains(warning);
    }
    public void setCache(ArrayList<WeatherWarning> cache) {
        cacheTimer();
        this.cache = cache;
    }

    public void addWarning(WeatherWarning warning) {
        this.cache.add(warning);
    }

    public WeatherWarning getWarning(String district, String type) {
        for (WeatherWarning warning : this.cache) {
            if (warning.getWarningType().equals(type) && warning.getDistrict().equals(district)) {
                this.hits++;
                return warning;
            }
        }
        this.misses++;
        return null;
    }
    public int getCacheSize() {
        return this.cache.size();
    }
    public void clearCache() {
        this.cache.clear();
    }

    public void cacheTimer(){
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        cache.clear();
                    }
                },
                100000
        );
    }
}
