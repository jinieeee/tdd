package com.example.tdd.money.domain;

public abstract class Money {
    // 상속받은 클래스에서 변수를 사용할 수 있도록 접근성을 protected로
    protected int amount;

    protected String currency;

    public boolean equals(Object object) {
        Money money = (Money) object;
        return amount == money.amount;
    }

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money dollar(int amount) {
        return new Dollar(amount, "USD");
    }

    public static Money franc(int amount) {
        return new Franc(amount, "CHF");
    }

    public abstract Money times(int multiplier);

    public String currency() {
        return currency;
    }
}
