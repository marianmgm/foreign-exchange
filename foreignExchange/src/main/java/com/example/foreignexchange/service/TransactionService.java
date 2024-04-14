package com.example.foreignexchange.service;

import com.example.foreignexchange.models.Transaction;
import com.example.foreignexchange.models.TransactionFilterOptions;

import java.util.List;

public interface TransactionService {
    Double getTargetAmount(String source, Double sourceAmount, String target);

    Double parseTargetAmountFromJson(String jsonResponse);

    Transaction create(String source,Double sourceAmount, String target);
    Transaction getById(int id);
    List<Transaction> getFilteredTransactions(TransactionFilterOptions filterOptions);

}
