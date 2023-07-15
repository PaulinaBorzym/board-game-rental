package com.project.service;

import com.google.gson.*;
import com.project.config.Config;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class DescriptionService {


    @Autowired
    private Config config;

    @Autowired
    public DescriptionService() {
    }

    public String getDescription(String language, String description) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/x-www-form-urlencoded");
        headers.add("Accept-Encoding", "application/gzip");
        headers.add("X-RapidAPI-Key", config.getXRapidAPIKey());
        headers.add("X-RapidAPI-Host", config.getXRapidAPIHost());
        HttpEntity<String> entity = new HttpEntity<>("q="+description+"&target="+language,headers);
        String result = restTemplate.postForObject("https://google-translate1.p.rapidapi.com/language/translate/v2",entity,String.class);
        return getTransletedText(result);
    }

    private String getTransletedText(String result) {
        JsonObject jobj = new Gson().fromJson(result,JsonObject.class);
        return jobj.get("data").getAsJsonObject().get("translations").getAsJsonArray()
                .get(0).getAsJsonObject().get("translatedText").getAsString();
    }

}
