package com.example.tdd.money.domain;

public abstract class Expression {
    abstract Money reduce(Bank bank, String to);

    public Expression plus(Expression addend) {
        return new Sum(this, addend);
    };

    abstract Expression times(int multiplier);
}
