package com.example.foreignexchange.service;

import com.example.foreignexchange.models.ExchangeRate;
import com.example.foreignexchange.repository.ExchangeRateRepository;
import com.example.foreignexchange.utils.CurrencyCodeValidator;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService{
    private static final String API_KEY = "68271c6d7da50fa9f1ddcab9";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY+ "/pair/";
    private final ExchangeRateRepository exchangeRateRepository;
    private RestTemplate restTemplate;
    @Autowired
    public ExchangeRateServiceImpl(ExchangeRateRepository exchangeRateRepository, RestTemplate restTemplate) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.restTemplate = restTemplate;
    }



    @Override
    public Double getExchangeRate(String source, String target) {
        String apiUrl = API_URL +
                source+ "/"+target;

        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);

        return parseExchangeRateFromJson(jsonResponse);
    }

    @Override
    public Double parseExchangeRateFromJson(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        if (jsonObject.has("conversion_rate")) {
            return jsonObject.getDouble("conversion_rate");
        } else {
            throw new IllegalArgumentException("Conversion rate not found in JSON response");
        }

    }

    @Override
    public ExchangeRate create(String source, String target){
        CurrencyCodeValidator.validateCurrencyCodes(source,target);
        ExchangeRate exchangeRate=new ExchangeRate();
        exchangeRate.setSource(source);
        exchangeRate.setTarget(target);
        exchangeRate.setRate(getExchangeRate(source,target));
        exchangeRate.setTimestamp(LocalDateTime.now());
        exchangeRateRepository.create(exchangeRate);
        return exchangeRate;
    }



}
