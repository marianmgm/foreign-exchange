package com.example.foreignexchange.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request payload for generating an exchange rate")
public class ExchangeRateRequest {

    @Schema(description = "Source currency code", example = "EUR", required = true)
    private String sourceCurrency;

    @Schema(description = "Target currency code", example = "BGN", required = true)
    private String targetCurrency;

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
}

