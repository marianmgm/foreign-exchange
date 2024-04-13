package com.example.foreignexchange.service;

import com.example.foreignexchange.models.Transaction;
import com.example.foreignexchange.models.TransactionFilterOptions;
import com.example.foreignexchange.repository.TransactionRepository;
import com.example.foreignexchange.utils.CurrencyCodeValidator;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private static final String API_KEY = "68271c6d7da50fa9f1ddcab9";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY+ "/pair/";

    private final TransactionRepository transactionRepository;
    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Double getTargetAmount(String source, Double sourceAmount, String target) {
        String apiUrl = API_URL +
                source+ "/"+target+ "/"+sourceAmount;
        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
        return parseTargetAmountFromJson(jsonResponse);
    }

    @Override
    public Double parseTargetAmountFromJson(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        if (jsonObject.has("conversion_result")) {
            return jsonObject.getDouble("conversion_result");
        }else {
            throw new IllegalArgumentException("Conversion result not found in JSON response");
        }
    }

    @Override
    public Transaction create(String source, Double sourceAmount, String target) {
        CurrencyCodeValidator.validateCurrencyCodes(source,target);
        Transaction transaction=new Transaction();
        transaction.setSourceCurrency(source);
        transaction.setSourceAmount(sourceAmount);
        transaction.setTargetCurrency(target);
        transaction.setTargetAmount(getTargetAmount(source,sourceAmount,target));
        transaction.setTimestamp(LocalDateTime.now());
        transactionRepository.create(transaction);
        return transaction;
    }

    @Override
    public Transaction getById(int id) {
        return transactionRepository.getById(id);
    }
    @Override
    public List<Transaction> getFilteredTransactions(TransactionFilterOptions filterOptions) {
        return transactionRepository.getFilteredTransactions(filterOptions);
    }
}
