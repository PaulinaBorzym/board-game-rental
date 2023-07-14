package com.project.controller;

import com.project.domain.Game;
import com.project.dto.GameDto;
import com.project.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Currency;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/currency")
public class CurrencyController {

    private final CurrencyService service;

    @GetMapping
    public ResponseEntity <Void> getCurrencies(){
        service.getFromAPI();
        return ResponseEntity.ok().build();
    }
}
