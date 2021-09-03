package com.tinkoff.edu.app.model;

import com.tinkoff.edu.app.enums.*;

public class LoanRequest {
    private final LoanType type;
    private final int months;
    private final int amount;
    private final String fio;

    public LoanRequest(LoanType type, int months, int amount,String fio) {
        this.type = type;
        this.months = months;
        this.amount = amount;
        this.fio = fio;
    }

    public int getMonths() {
        return months;
    }

    public LoanType getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public String getFio() {
        return fio;
    }

    public String toString() {
        return "Request: {"
                + this.type +
                ", $"
                + this.getAmount()
                + " for " + this.getMonths() + " months}";
    }
}
