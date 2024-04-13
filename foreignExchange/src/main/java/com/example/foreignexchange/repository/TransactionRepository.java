package com.example.foreignexchange.repository;

import com.example.foreignexchange.models.Transaction;
import com.example.foreignexchange.models.TransactionFilterOptions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository  {
    Transaction getById(int id);
    void create(Transaction transaction);
    List<Transaction> getFilteredTransactions(TransactionFilterOptions filterOptions);

}
