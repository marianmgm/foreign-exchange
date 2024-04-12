package com.example.foreignexchange.repository;

import com.example.foreignexchange.models.ExchangeRate;

public interface ExchangeRateRepository {
    ExchangeRate getById(int id);
    void create(ExchangeRate exchangeRate);
    void update(ExchangeRate exchangeRate);

    void delete(int id);
}
