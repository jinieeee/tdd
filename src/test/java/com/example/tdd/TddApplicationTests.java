package com.example.tdd;

import com.example.tdd.money.domain.Dollar;
import com.example.tdd.money.domain.Franc;
import com.example.tdd.money.domain.Money;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TddApplicationTests {

	@Test
	public void testMultiplication() {
		Money five = Money.dollar(5);
		// Dollar와 Dollar을 비교함으로써 amount를 캡슐화
		assertEquals(Money.dollar(5), five.times(2));
		assertEquals(Money.dollar(15), five.times(3));
	}

	/**
	 * 동치성 테스트
	 */
	@Test
	public void testEquality() {
		assertTrue(Money.dollar(5).equals(Money.dollar(5)));
		assertFalse(Money.dollar(5).equals(Money.dollar(5)));

		// 동치성 테스트에서 Franc 추가
		assertTrue(Money.franc(5).equals(Money.franc(5)));
		assertFalse(Money.franc(5).equals(Money.franc(5)));

		assertFalse(Money.franc(5).equals(Money.dollar(5)));
	}

	/**
	 * 새로운 화폐 Franc
	 */
	@Test
	public void testFrancMultiplication() {
		Money five = Money.franc(5);
		assertEquals(Money.franc(10), five.times(2));
		assertEquals(Money.franc(15), five.times(3));
	}

	@Test
	public void testCurrency() {
		assertEquals("USD", Money.dollar(1).currency());
		assertEquals("CHF", Money.franc(1).currency());
	}

	@Test
	public void testDifferentClassEquality() {
		/* equals는 amount와 currency가 동일한지 비교하는 메소드이다.
		*  Franc의 생성자는 Money를 상속받아 Money 객체를 리턴받도록 구현되어 있기 때문에 비교하면 결과는 True이다.
		* */
		assertTrue(new Money(10, "CHF").equals(new Franc(10, "CHF")));
	}
}