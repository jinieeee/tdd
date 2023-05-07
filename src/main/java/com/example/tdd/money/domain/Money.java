package com.example.tdd.money.domain;

public class Money {
    // 상속받은 클래스에서 변수를 사용할 수 있도록 접근성을 protected로
    protected int amount;


    public boolean equals(Object object) {
        Money money = (Money) object;
        return amount == money.amount;
    }
}
