package com.project.controller;

import com.project.exeption.CurrencyNotFoundException;
import com.project.service.CurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig
@SpringBootTest
@AutoConfigureMockMvc
class CurrencyControllerTestSuite {
    private MockMvc mockMvc;
    @Mock
    private CurrencyService currencyService;
    @InjectMocks
    private CurrencyController currencyController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(currencyController).build();
    }

    @Test
    void shouldGetCurrencies() throws Exception, CurrencyNotFoundException {
        // Given
        doNothing().when(currencyService).getFromAPI();

        // When & Then
        mockMvc.perform(get("/v1/currency")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}