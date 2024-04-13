package com.example.foreignexchange.controllers;

import com.example.foreignexchange.models.ExchangeRate;
import com.example.foreignexchange.service.ExchangeRateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @Operation(summary = "Generate an exchange rate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exchange rate generated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExchangeRate.class))}),
    })
    public ResponseEntity<ExchangeRate> create(
            @PathVariable String source,
            @PathVariable String target) {
        ExchangeRate createdExchangeRate = exchangeRateService.create(source, target);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExchangeRate);
    }



}
