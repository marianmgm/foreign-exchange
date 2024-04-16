package com.example.foreignexchange.controllers;

import com.example.foreignexchange.dto.TransactionRequest;
import com.example.foreignexchange.dto.TransactionResponse;
import com.example.foreignexchange.exceptions.EntityNotFoundException;
import com.example.foreignexchange.exceptions.InvalidCurrencyCodeException;
import com.example.foreignexchange.models.ExchangeRate;
import com.example.foreignexchange.models.Transaction;
import com.example.foreignexchange.models.TransactionFilterOptions;
import com.example.foreignexchange.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
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
    @Operation(summary = "Generates a transaction based on a  given source code, target code and source amount")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transaction generated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TransactionResponse.class))}),
    })
    public ResponseEntity<TransactionResponse> create(
            @PathVariable("source") @Parameter(description = "Source currency code",
                    example = "EUR", required = true) String source,
            @PathVariable("target") @Parameter(description = "Target currency code",
                    example = "BGN", required = true) String target,
            @PathVariable("amount") @Parameter(description = "Source amount",
                    example = "100.00", required = true) Double amount)
    {
        try {
            Transaction createdTransaction = transactionService.create(source, amount, target);
            return ResponseEntity.status(HttpStatus.CREATED).body(new TransactionResponse(createdTransaction.getId(), createdTransaction.getTargetAmount()));
        }catch (InvalidCurrencyCodeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    @PostMapping("/")
    @Operation(summary = "Generates a transaction based on given source code, target code, and source amount without parameters. Version 2 of the POST controller for a more RESTful API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transaction generated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TransactionResponse.class))}),
    })
    public ResponseEntity<TransactionResponse> createTransaction(
            @RequestBody TransactionRequest request) {
        try {
            Transaction createdTransaction = transactionService.create(request.getSourceCurrency(),request.getAmount(),request.getTargetCurrency());
            return ResponseEntity.status(HttpStatus.CREATED).body(new TransactionResponse(createdTransaction.getId(), createdTransaction.getTargetAmount()));
        } catch (InvalidCurrencyCodeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Gets a transaction by its unique Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get transaction by Id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Transaction.class),
                            examples = @ExampleObject(value = "{\n  \"id\": 3,\n  \"source\": \"USD\",\n  \"target\": \"EUR\",\n  \"sourceAmount\": 100.0,\n \"targetAmount\": 85.0,\n  \"timestamp\": \"2024-04-10 12:00:00\"\n}"))}),
    })
    public Transaction get (@Parameter(description = "Transaction ID to fetch", example = "3") @PathVariable int id) {
        try {
            return transactionService.getById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    @Operation(summary = "A paginated list of currency conversions filtered by transaction date or between two dates.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns a paginated list by given criteria",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Transaction.class))}),
    })
    @GetMapping("/filter")
    public ResponseEntity<List<Transaction>> getFilteredTransactions(TransactionFilterOptions filterOptions) {
        List<Transaction> filteredTransactions = transactionService.getFilteredTransactions(filterOptions);
        return ResponseEntity.ok(filteredTransactions);
    }




}
