package com.project.controller;

import com.project.domain.Currency;
import com.project.exeption.CurrencyNotFoundException;
import com.project.service.CurrencyService;
import com.project.enums.CurrencyValue;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/currency")
public class CurrencyController {
    private final CurrencyService service;

    @GetMapping
    public ResponseEntity<Void> getCurrencies() throws CurrencyNotFoundException {
        service.getFromAPI();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{code}")
    public ResponseEntity<Void> setCurrency(@PathVariable String code) throws CurrencyNotFoundException {
        Currency currency = service.getCurrencyByCode(code);
        CurrencyValue.INSTANCE.setValue(currency.getValue());
        return ResponseEntity.ok().build();
    }
}
