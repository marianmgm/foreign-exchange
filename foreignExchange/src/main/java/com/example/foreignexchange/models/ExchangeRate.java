package com.example.foreignexchange.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name="exchange_rate")
public class ExchangeRate {
    @Id
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
}
