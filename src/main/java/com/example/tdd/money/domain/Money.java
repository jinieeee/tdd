package com.example.tdd.money.domain;

import java.util.Objects;

public class Money extends Expression{
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

    public Expression times(int multiplier) {
        return new Money(amount * multiplier , currency);
    }

    public String currency() {
        return currency;
    }

    public String toString() {
        return amount + " " + currency;
    }

    public Money reduce(Bank bank, String to) {
        // Money가 환율을 알게 되는 나쁜 코드 -> 환율 계산은 Bank로 이동
        // int rate = (currency.equals("CHF") && to.equals("USD")) ? 2 : 1;

        int rate = bank.rate(currency, to);
        return new Money(amount / rate, to);
    }

}
