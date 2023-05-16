package com.example.tdd.money.domain;

public class Franc extends Money {

    private String currency;

    public Franc(int amount, String currency) {
        super(amount, currency);
    }

    public Money times(int multiplier) {
        return Money.franc(amount * multiplier);
    }

    @Override
    public String currency() {
        return "CHF";
    }
}
