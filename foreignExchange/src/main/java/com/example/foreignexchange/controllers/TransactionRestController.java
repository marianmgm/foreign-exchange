package com.example.foreignexchange.controllers;

import com.example.foreignexchange.exceptions.EntityNotFoundException;
import com.example.foreignexchange.models.ExchangeRate;
import com.example.foreignexchange.models.Transaction;
import com.example.foreignexchange.models.TransactionFilterOptions;
import com.example.foreignexchange.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@Tag(name="Transactions")
public class TransactionRestController {
    private final TransactionService transactionService;
    @Autowired
    public TransactionRestController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @PostMapping("/{source}/{target}/{amount}")
    @Operation(summary = "Generate a transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction generated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Transaction.class))}),
    })
    public ResponseEntity<Transaction> create(
            @PathVariable String source,
            @PathVariable String target,
            @PathVariable Double amount) {
        Transaction createdTransaction = transactionService.create(source,amount, target);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTransaction);
    }
    @GetMapping("/{id}")
    public Transaction get(@PathVariable int id) {
        try {
            return transactionService.getById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    @GetMapping("/filter")
    public ResponseEntity<List<Transaction>> getFilteredTransactions(TransactionFilterOptions filterOptions) {
        List<Transaction> filteredTransactions = transactionService.getFilteredTransactions(filterOptions);
        return ResponseEntity.ok(filteredTransactions);
    }




}
