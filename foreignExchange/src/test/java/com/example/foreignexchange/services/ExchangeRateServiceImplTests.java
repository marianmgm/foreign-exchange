package com.example.foreignexchange.services;


import com.example.foreignexchange.models.ExchangeRate;

import com.example.foreignexchange.repository.ExchangeRateRepository;
import com.example.foreignexchange.service.ExchangeRateServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class ExchangeRateServiceImplTests {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ExchangeRateRepository exchangeRateRepository;

    @InjectMocks
    private ExchangeRateServiceImpl exchangeRateService;


    @Test
    void testCreateExchangeRate_ShouldCallRepository() {
        // Arrange
        String sourceCurrency = "EUR";
        String targetCurrency = "BGN";
        String jsonResponse = "{\"conversion_rate\": 1.95583}";

        Mockito.doNothing().when(exchangeRateRepository).create(Mockito.any(ExchangeRate.class));

        // Act
        ExchangeRate createdExchangeRate = exchangeRateService.create(sourceCurrency, targetCurrency);

        // Assert
        Mockito.verify(exchangeRateRepository, Mockito.times(1)).create(Mockito.any(ExchangeRate.class));
        Assertions.assertEquals(sourceCurrency, createdExchangeRate.getSource());
        Assertions.assertEquals(targetCurrency, createdExchangeRate.getTarget());
        Assertions.assertEquals(1.9558, createdExchangeRate.getRate(), 0.0001);
        Assertions.assertNotNull(createdExchangeRate.getTimestamp());
    }

    @Test
    void testParseExchangeRateFromJson_ShouldReturnExchangeRate() {
        // Arrange
        String jsonResponse = "{\"conversion_rate\": 1.95583}";

        // Act
        Double exchangeRate = exchangeRateService.parseExchangeRateFromJson(jsonResponse);

        // Assert
        Assertions.assertEquals(1.9558, exchangeRate, 0.0001);
    }



}
