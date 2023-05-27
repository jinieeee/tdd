package com.example.tdd.money.domain;

public class Bank {

    public Money reduce(Expression source, String to) {
//         다형성을 활용해서 캐스팅과 클래스 검사 코드 제거
//        if(source instanceof Money)
//            return (Money) source.reduce(to);
//        Sum sum = (Sum) source;
        return source.reduce(to);
    }
}
