package com.example.foreignexchange.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request payload for creating a transaction")
public class TransactionRequest {

    @Schema(example = "EUR", required = true) //test
    private String sourceCurrency;

    @Schema(example = "BGN", required = true)
    private String targetCurrency;

    @Schema(example = "100.00", required = true)
    private Double amount;

    public TransactionRequest() {
    }

    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}

