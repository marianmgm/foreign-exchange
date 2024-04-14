package com.example.foreignexchange.services;

import com.example.foreignexchange.models.Transaction;
import com.example.foreignexchange.repository.TransactionRepository;
import com.example.foreignexchange.service.TransactionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;


import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTests {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;



    @Test
    void parseTargetAmountFromJson_Should_ReturnCorrectAmount() {
        // Arrange
        String jsonResponse = "{\"conversion_result\": 51.13}";

        // Act
        Double targetAmount = transactionService.parseTargetAmountFromJson(jsonResponse);

        // Assert
        Assertions.assertEquals(51.13, targetAmount);
    }

    @Test
    void create_Should_CreateTransactionSuccessfully() {
        // Arrange
        String sourceCurrency = "BGN";
        Double sourceAmount = 100.0;
        String targetCurrency = "EUR";

        Transaction mockTransaction = new Transaction();
        mockTransaction.setId(1);
        mockTransaction.setSourceCurrency(sourceCurrency);
        mockTransaction.setSourceAmount(sourceAmount);
        mockTransaction.setTargetCurrency(targetCurrency);
        mockTransaction.setTargetAmount(51.13);
        mockTransaction.setTimestamp(LocalDateTime.now());



        // Act
        Transaction createdTransaction = transactionService.create(sourceCurrency, sourceAmount, targetCurrency);

        // Assert
        Assertions.assertNotNull(createdTransaction);
        Assertions.assertEquals(sourceCurrency, createdTransaction.getSourceCurrency());
        Assertions.assertEquals(sourceAmount, createdTransaction.getSourceAmount());
        Assertions.assertEquals(targetCurrency, createdTransaction.getTargetCurrency());
        Assertions.assertEquals(51.13, createdTransaction.getTargetAmount());
        Assertions.assertNotNull(createdTransaction.getTimestamp());
    }

    @Test
    void getById_Should_ReturnTransaction() {
        // Arrange
        int transactionId = 1;
        Transaction mockTransaction = new Transaction();
        mockTransaction.setId(transactionId);

        when(transactionRepository.getById(transactionId)).thenReturn(mockTransaction);

        // Act
        Transaction retrievedTransaction = transactionService.getById(transactionId);

        // Assert
        Assertions.assertNotNull(retrievedTransaction);
        Assertions.assertEquals(transactionId, retrievedTransaction.getId());
    }


}
