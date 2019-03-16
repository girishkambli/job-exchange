package com.jobexchange.model;

import java.math.BigDecimal;
import java.util.Currency;

public class Amount {

    private Currency currency;
    private BigDecimal value;

    public Amount(Currency currency, BigDecimal value) {
        this.currency = currency;
        this.value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Amount{" +
            "currency=" + currency +
            ", value=" + value +
            '}';
    }
}
