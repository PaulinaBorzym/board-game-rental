package com.project.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.project.domain.Currency;
import com.project.enums.SupportedCurrencies;
import com.project.exeption.CurrencyNotFoundException;
import com.project.repository.CurrencyRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
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

    public List<Currency> getAllCurrencies() {
        return repository.findAll();
    }

    public Currency getCurrencyByCode(String code) throws CurrencyNotFoundException {
        return repository.findByCurrencyCode(code).orElseThrow(CurrencyNotFoundException::new);
    }

    @PostConstruct
    @Scheduled(cron = "0 0 1 * * *")
    public void getFromAPI() throws CurrencyNotFoundException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> rs = restTemplate.getForEntity("https://open.er-api.com/v6/latest/PLN", String.class);
        JsonParser jp = new JsonParser();
        InputStream inputStream = new ByteArrayInputStream(rs.getBody().getBytes());
        JsonElement je = jp.parse(new InputStreamReader(inputStream));
        JsonObject object = je.getAsJsonObject();
        saveAllCurrencies(object.get("rates").getAsJsonObject());
    }

    private void saveAllCurrencies(JsonObject rates) throws CurrencyNotFoundException {
        List<Currency> currencies = getAllCurrencies();
        if (currencies.size() == 3) {
            for (SupportedCurrencies currency : SupportedCurrencies.values()) {
                Double value = rates.get(currency.name()).getAsDouble();
                Currency currentCurrency = getCurrencyByCode(currency.name());
                currentCurrency.setValue(value);
                repository.save(currentCurrency);
            }
            return;
        }
        for (SupportedCurrencies currency : SupportedCurrencies.values()) {
            Double value = rates.get(currency.name()).getAsDouble();
            repository.save(new Currency(currency.name(), value));
        }
    }
}