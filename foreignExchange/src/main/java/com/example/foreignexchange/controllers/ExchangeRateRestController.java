package com.example.foreignexchange.controllers;

import com.example.foreignexchange.dto.ExchangeRateRequest;
import com.example.foreignexchange.dto.ExchangeRateResponse;
import com.example.foreignexchange.exceptions.InvalidCurrencyCodeException;
import com.example.foreignexchange.models.ExchangeRate;
import com.example.foreignexchange.service.ExchangeRateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

@RestController
@RequestMapping("/api/exchangeRate")
@Tag(name = "ExchangeRates")
public class ExchangeRateRestController{
    private final ExchangeRateService exchangeRateService;

    @Autowired
    public ExchangeRateRestController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }
    @PostMapping("/{source}/{target}")
    @Operation(summary = "Generates an exchange rate based on a given source code and target code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Exchange rate generated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExchangeRateResponse.class))}),
    })
    public ResponseEntity<ExchangeRateResponse> create(
            @PathVariable("source") @Parameter(description = "Source currency code",
                    example = "EUR", required = true) String source,
            @PathVariable("target") @Parameter(description = "Target currency code",
                    example = "BGN", required = true) String target) {
        try {
            ExchangeRate createdExchangeRate = exchangeRateService.create(source, target);
//            return ResponseEntity.status(HttpStatus.CREATED).body(createdExchangeRate);
//        }catch (InvalidCurrencyCodeException e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
            return ResponseEntity.status(HttpStatus.CREATED).body(new ExchangeRateResponse(createdExchangeRate.getRate()));
        } catch (InvalidCurrencyCodeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    @PostMapping()
    @Operation(summary = "Generates an exchange rate based on a given source code and target code. " +
            "Version 2 of the POST controller for a more RESTful API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Exchange rate generated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExchangeRateResponse.class))}),
    })
    public ResponseEntity<ExchangeRateResponse> createExchangeRate(
            @RequestBody ExchangeRateRequest request) {
        try {
            ExchangeRate createdExchangeRate = exchangeRateService.create(request.getSourceCurrency(), request.getTargetCurrency());
            return ResponseEntity.status(HttpStatus.CREATED).body(new ExchangeRateResponse(createdExchangeRate.getRate()));
        } catch (InvalidCurrencyCodeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }



}
