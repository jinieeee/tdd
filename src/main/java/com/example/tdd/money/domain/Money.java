package com.example.tdd.money.domain;

import java.util.Objects;

public class Money implements Expression{
    // 상속받은 클래스에서 변수를 사용할 수 있도록 접근성을 protected로
    protected int amount;

    protected String currency;

    public boolean equals(Object object) {
        Money money = (Money) object;
        return amount == money.amount
                && currency().equals(money.currency());
    }

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    public static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

    public Money times(int multiplier) {
        return new Money(amount * multiplier , currency);
    }

    public String currency() {
        return currency;
    }

    public String toString() {
        return amount + " " + currency;
    }

    public Expression plus(Money addend) {
        return new Money(amount + addend.amount, currency);
    }
}
