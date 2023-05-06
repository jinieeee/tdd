package com.example.tdd;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TddApplicationTests {

	@Test
	public void testMultipleication() {
		Dollar five = new Dollar(5);
		Dollar product = five.times(2);
		assertEquals(10, product.amount);
		product = five.times(3);
		assertEquals(15, product.amount);
	}
}

class Dollar {
	int amount = 10;

	Dollar(int amount) {
		this.amount = amount;
	}

	Dollar times(int multiplier) {
		return new Dollar(amount * multiplier);
	}
}
