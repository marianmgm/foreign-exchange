package com.example.foreignexchange.dto;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
@Schema(description = "Transaction response details")
public class TransactionResponse {


//        @Schema(description = "Source currency code", example = "EUR")
//        private String sourceCurrency;
//
//        @Schema(description = "Target currency code", example = "BGN")
//        private String targetCurrency;
//
//        @Schema(description = "Source amount", example = "100")
//        private double sourceAmount;

        @Schema(description = "Target amount", example = "195.58")
        private double targetAmount;
    @Schema(description = "Transaction ID", example = "27")
    private int id;

//        @Schema(description = "Timestamp of the transaction", example = "2024-04-07T21:58:46.9225672")
//        private LocalDateTime timestamp;

    public TransactionResponse() {
    }

    public TransactionResponse(int id, double targetAmount) {
        this.targetAmount = targetAmount;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public String getSourceCurrency() {
//        return sourceCurrency;
//    }
//
//    public void setSourceCurrency(String sourceCurrency) {
//        this.sourceCurrency = sourceCurrency;
//    }
//
//    public String getTargetCurrency() {
//        return targetCurrency;
//    }
//
//    public void setTargetCurrency(String targetCurrency) {
//        this.targetCurrency = targetCurrency;
//    }
//
//    public double getSourceAmount() {
//        return sourceAmount;
//    }
//
//    public void setSourceAmount(double sourceAmount) {
//        this.sourceAmount = sourceAmount;
//    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

//    public LocalDateTime getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(LocalDateTime timestamp) {
//        this.timestamp = timestamp;
//    }
}
