package com.example.foreignexchange.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response payload for an exchange rate")
public class ExchangeRateResponse {

    @Schema(example = "EUR", required = true)
    private String sourceCurrency;

    @Schema(example = "BGN", required = true)
    private String targetCurrency;

    @Schema(example = "1.9558", required = true)
    private Double rate;

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

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}

