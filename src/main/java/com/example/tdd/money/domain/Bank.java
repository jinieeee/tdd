package com.example.tdd.money.domain;

import java.util.Hashtable;

public class Bank {
    // 환율을 저장할 해시테이블
    private Hashtable rates = new Hashtable();

    public Money reduce(Expression source, String to) {
//         다형성을 활용해서 캐스팅과 클래스 검사 코드 제거
//        if(source instanceof Money)
//            return (Money) source.reduce(to);
//        Sum sum = (Sum) source;

//        환율 계산을 위해 인자로 bank 추가
        return source.reduce(this, to);
    }
    public void addRate(String from, String to, int rate) {
        rates.put(new Pair(from, to), rate);
    }

    public int rate(String from, String to) {
        if(from.equals(to)) return 1;
        Integer rate = (Integer) rates.get(new Pair(from, to));
        return rate.intValue();
    }
}
