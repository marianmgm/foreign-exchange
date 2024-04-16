package com.example.foreignexchange.service;

import com.example.foreignexchange.models.ExchangeRate;

public interface ExchangeRateService {

    Double getExchangeRate(String source, String target);

    Double parseExchangeRateFromJson(String jsonResponse);

    ExchangeRate create(String source, String target);

    ExchangeRate getById(int id);
}
