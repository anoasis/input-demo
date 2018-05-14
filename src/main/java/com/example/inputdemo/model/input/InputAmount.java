package com.example.inputdemo.model.input;

import java.math.BigDecimal;

public class InputAmount extends InputValue {

    private double amount;
    private CurrencyCode currencyCode;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public CurrencyCode getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(CurrencyCode currencyCode) {
        this.currencyCode = currencyCode;
    }

}
