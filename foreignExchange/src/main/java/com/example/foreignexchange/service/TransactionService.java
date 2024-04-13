package com.example.foreignexchange.service;

import com.example.foreignexchange.models.Transaction;

public interface TransactionService {
    Double getTargetAmount(String source, Double sourceAmount, String target);

    Double parseTargetAmountFromJson(String jsonResponse);

    Transaction create(String source,Double sourceAmount, String target);
    Transaction getById(int id);
    // TODO: 13.4.2024 г. filter options

}
