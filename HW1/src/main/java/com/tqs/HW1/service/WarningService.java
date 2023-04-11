package com.tqs.HW1.service;

import com.tqs.HW1.cache.Cache;
import com.tqs.HW1.model.WeatherWarning;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class WarningService {

    private Cache cache = new Cache();

    public ArrayList<WeatherWarning> getAllWarnings() throws IOException, InterruptedException {
        if(cache.getCache().size() > 0) {
            cache.addHit();
            return cache.getCache();
        }else{
            cache.addMiss();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.ipma.pt/open-data/forecast/warnings/warnings_www.json"))
                    .build();
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONArray jsonArr = new JSONArray(httpResponse.body().toString());
            cache.setCache(processWarning(jsonArr));
            return processWarning(jsonArr);
        }
    }


    public ArrayList<WeatherWarning> getDistrictWarnings(String district) throws IOException, InterruptedException {
        ArrayList<WeatherWarning> warnings = new ArrayList<>();
        if(cache.getCache().size() > 0) {
            cache.addHit();
            warnings = cache.getCache();
        }else {
            cache.addMiss();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.ipma.pt/open-data/forecast/warnings/warnings_www.json"))
                    .build();
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONArray jsonArr = new JSONArray(httpResponse.body().toString());
            warnings = processWarning(jsonArr);
        }
        ArrayList<WeatherWarning> res = new ArrayList<>();
        for (int i = 0; i < warnings.size(); i++) {
            WeatherWarning warning = warnings.get(i);
            if (warning.getDistrict().equals(district.toString())) {
                res.add(warning);
            }

        }

        return res;
    }

    public ArrayList<WeatherWarning> getWarningbyLevel(String level) throws IOException, InterruptedException {
        ArrayList<WeatherWarning> warnings = new ArrayList<>();
        if(cache.getCache().size() > 0) {
            cache.addHit();
            warnings = cache.getCache();
        }else {
            cache.addMiss();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.ipma.pt/open-data/forecast/warnings/warnings_www.json"))
                    .build();
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONArray jsonArr = new JSONArray(httpResponse.body().toString());
            warnings = processWarning(jsonArr);
        }
        ArrayList<WeatherWarning> res = new ArrayList<>();
        for (int i = 0; i < warnings.size(); i++) {
            WeatherWarning warning = warnings.get(i);
            if (warning.getLevel().equals(level.toString())) {
                res.add(warning);
            }
        }

        return res;
    }

    public ArrayList<WeatherWarning> getWarningbyType(String type) throws IOException, InterruptedException {
        ArrayList<WeatherWarning> warnings = new ArrayList<>();
        if(cache.getCache().size() > 0) {
            cache.addHit();
            warnings = cache.getCache();
        }else {
            cache.addMiss();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.ipma.pt/open-data/forecast/warnings/warnings_www.json"))
                    .build();
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONArray jsonArr = new JSONArray(httpResponse.body().toString());
            warnings = processWarning(jsonArr);
        }
        ArrayList<WeatherWarning> res = new ArrayList<>();
        for(int i = 0; i < warnings.size(); i++) {
            WeatherWarning warning = warnings.get(i);
            if(warning.getWarningType().equals(type.toString())) {
                res.add(warning);
            }
        }
        return res;
    }


    public ArrayList<WeatherWarning> processWarning(JSONArray jsonArray) throws IOException, InterruptedException {
        ArrayList<WeatherWarning> res = new ArrayList<>();
        HashMap<String, String> districtConv = new HashMap<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.ipma.pt/open-data/distrits-islands.json"))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject jsonObject = new JSONObject(httpResponse.body().toString());
        JSONArray jsonArr = (JSONArray) jsonObject.get("data");
        for(int i = 0 ; i< jsonArr.length(); i++ ) {
            JSONObject jsonobject = jsonArr.getJSONObject(i);
            String districtId = (String) jsonobject.get("idAreaAviso");
            String districtName = (String) jsonobject.get("local");
            if(districtConv.containsKey(districtId)) {

            }else {
                districtConv.put(districtId, districtName);
            }
        }

        for(int i = 0 ; i< jsonArray.length(); i++ ) {
            WeatherWarning warning = new WeatherWarning();
            JSONObject jsonobj = jsonArray.getJSONObject(i);
            String districtId = (String) jsonobj.get("idAreaAviso");
            String district = districtConv.get(districtId);
            String warningType = (String) jsonobj.get("awarenessTypeName");
            String text = (String) jsonobj.get("text");
            String level = (String) jsonobj.get("awarenessLevelID");
            warning.setWarningType(warningType);
            warning.setLevel(level);
            warning.setDistrict(district);
            if(text.equals("")) {
                warning.setText("No particular weather advice");
            } else {
                warning.setText(text);
            }
            res.add(warning);
        }
        return res;

    }

    public HashMap<String , Integer> getCacheInfo() {
        HashMap<String, Integer> cacheInfo = new HashMap<>();
        cacheInfo.put("misses", cache.getMisses());
        cacheInfo.put("hits", cache.getHits());
        cacheInfo.put("requests", cache.getRequests());
        return cacheInfo;
    }



}

