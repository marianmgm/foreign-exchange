package com.example.foreignexchange.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="transaction_id")
    private int id;

    @Column(name="source_currency")
    private String sourceCurrency;
    @Column(name="target_currency")
    private String targetCurrency;

    @Column(name="source_amount")
    private double sourceAmount;
    @Column(name="target_amount")
    private double targetAmount;

    @Column(name="time_stamp")
    private LocalDateTime timestamp;

    public Transaction() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getSourceAmount() {
        return sourceAmount;
    }

    public void setSourceAmount(Double sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    public Double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(Double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction transaction = (Transaction) o;
        return id == transaction.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
