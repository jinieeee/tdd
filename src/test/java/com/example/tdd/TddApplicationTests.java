package com.example.tdd;

import com.example.tdd.money.domain.Dollar;
import com.example.tdd.money.domain.Franc;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TddApplicationTests {

	@Test
	public void testMultipleication() {
		Dollar five = new Dollar(5);
		// Dollar와 Dollar을 비교함으로써 amount를 캡슐화
		assertEquals(new Dollar(5), five.times(2));
		assertEquals(new Dollar(15), five.times(3));
	}

	/**
	 * 동치성 테스트
	 */
	@Test
	public void testEquality() {
		assertTrue(new Dollar(5).equals(new Dollar(5)));
		assertFalse(new Dollar(5).equals(new Dollar(5)));

		// 동치성 테스트에서 Franc 추가
		assertTrue(new Franc(5).equals(new Franc(5)));
		assertFalse(new Franc(5).equals(new Franc(5)));
	}

	/**
	 * 새로운 화폐 Franc
	 */
	@Test
	public void testFrancMultiplication() {
		Franc five = new Franc(5);
		assertEquals(new Franc(10), five.times(2));
		assertEquals(new Franc(15), five.times(3));
	}
}