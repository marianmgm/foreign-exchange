package com.example.foreignexchange.models;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

public class TransactionFilterOptions {
    private LocalDate day;
    private LocalDate startDate;
    private LocalDate endDate;

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
