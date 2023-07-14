package com.project.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.project.domain.Currency;
import com.project.enums.SupportedCurrencies;
import com.project.repository.CurrencyRepository;
import com.project.utils.OpenErSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class CurrencyService {
    private CurrencyRepository repository;

    @Autowired
    public CurrencyService(CurrencyRepository repository) {
        this.repository = repository;
    }

    public List<Currency> getAllCurrencies(){
        return repository.findAll();
    }

    public void getFromAPI(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> rs = restTemplate.getForEntity("https://open.er-api.com/v6/latest/PLN", String.class);
        JsonParser jp = new JsonParser();
        InputStream inputStream = new ByteArrayInputStream(rs.getBody().getBytes());
        JsonElement je = jp.parse(new InputStreamReader(inputStream));
        JsonObject object = je.getAsJsonObject();
        saveAllCurrencies(object.get("rates").getAsJsonObject());
        Double aaa = object.get("rates").getAsJsonObject().get("USD").getAsDouble();
        System.out.println("Bla");
    }

    private void saveAllCurrencies(JsonObject rates) {
        for (SupportedCurrencies currency : SupportedCurrencies.values()){
            Double value = rates.get(currency.name()).getAsDouble();
            repository.save(new Currency(currency.name(),value));
        }

    }
}
