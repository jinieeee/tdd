package com.example.tdd;

import com.example.tdd.money.domain.Bank;
import com.example.tdd.money.domain.Expression;
import com.example.tdd.money.domain.Money;
import com.example.tdd.money.domain.Sum;
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
		assertFalse(Money.dollar(10).equals(Money.dollar(5)));

		// 동치성 테스트에서 Franc 추가
		// assertTrue(Money.franc(5).equals(Money.franc(5)));
		// assertFalse(Money.franc(5).equals(Money.franc(5)));

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

	/* Dollar / Franc 중복 제거로 인해 불필요해진 소스
	@Test
	public void testDifferentClassEquality() {
		// equals는 amount와 currency가 동일한지 비교하는 메소드이다.
		// Franc의 생성자는 Money를 상속받아 Money 객체를 리턴받도록 구현되어 있기 때문에 비교하면 결과는 True이다.
		assertTrue(new Money(10, "CHF").equals(new Franc(10, "CHF")));
	}
	*/


	@Test
	public void testSimpleAddition() {
		Money five = Money.dollar(5);
		Expression sum = five.plus(five);
		assertEquals(Money.dollar(10), sum);

		Bank bank = new Bank();
		Money reduced = bank.reduce(sum, "USD");
		assertEquals(Money.dollar(10), reduced);
	}

	@Test
	public void testPlusReturnsSum() {
		Expression five = Money.dollar(5);
		Expression result = five.plus(five);
		Sum sum = (Sum) result;
		assertEquals(five, sum.augend);
		assertEquals(five, sum.addend);
	}

	@Test
	public void testReduceSum() {
		Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
		Bank bank = new Bank();
		Money result = bank.reduce(sum, "USD");
		assertEquals(Money.dollar(7), result);

	}

	@Test
	public void testReduceMoney() {
		Bank bank = new Bank();
		Money result = bank.reduce(Money.dollar(1), "USD");
		assertEquals(Money.dollar(1), result);
	}

	@Test
	public void testReduceMoneyDefferentCurrency() {
		Bank bank = new Bank();
		// 환율이 2:1인 경우
		bank.addRate("CHF", "USD", 2);
		Money result = bank.reduce(Money.franc(2), "USD");
		assertEquals(Money.dollar(1), result);
	}

	@Test
	public void testArrayEquals() {
//		환율을 매핑시키는 해시 테이블을 사용할 수 있는가?
//		각각 원소에 대한 동치성 검사를 수행할 수 있는가? -> 실패
		assertEquals(new Object[]{"abc"}, new Object[]{"abc"});
	}

	@Test
	public void testIdentityRate() {
		assertEquals(1, new Bank().rate("USD", "USD"));
	}

	/**
	 * 서로 다른 통화 더하기
	 */
	@Test
	public void testMixedAddition() {
		Expression fiveBucks = Money.dollar(5);
		Expression tenFrancs = Money.franc(10);
		Bank bank = new Bank();
		bank.addRate("CHF", "USD", 2);
		Money result = bank.reduce(
				fiveBucks.plus(tenFrancs), "USD");
		assertEquals(Money.dollar(10), result);
	}

	@Test
	public void testSumPlusMoney() {
		// 스텁 구현한 sum.plus() 구현하고 테스트
		Expression fiveBucks = Money.dollar(5);
		Expression tenFrancs = Money.franc(10);
		Bank bank = new Bank();
		bank.addRate("CHF", "USD", 2);
		Expression sum = new Sum(fiveBucks, tenFrancs).plus(fiveBucks);
		Money result = bank.reduce(sum, "USD");
		assertEquals(Money.dollar(15), result);
	}

	@Test
	public void testSumTimes() {
		Expression fiveBucks = Money.dollar(5);
		Expression tenFrancs = Money.franc(10);
		Bank bank = new Bank();
		bank.addRate("CHF", "USD", 2);
		Expression sum = new Sum(fiveBucks, tenFrancs).times(2);
		Money result = bank.reduce(sum, "USD");
		assertEquals(Money.dollar(20), result);
	}

	@Test
	public void testPlusSameCurrencyReturnsMoney() {
		Expression sum = Money.dollar(1).plus(Money.dollar(1));
		// false
		assertTrue(sum instanceof Money);
	}
}