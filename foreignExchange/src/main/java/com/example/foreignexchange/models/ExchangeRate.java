package com.example.foreignexchange.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="exchange_rate")
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exchange_rate_id")
    private int id;

    @Column(name="source_currency")
    private String source;
    @Column(name="target_currency")
    private String target;

    @Column(name="rate")
    private double rate;

    @Column(name="time_stamp")
    private LocalDateTime timestamp;

    public ExchangeRate() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
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
        ExchangeRate exchangeRate = (ExchangeRate) o;
        return id == exchangeRate.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
