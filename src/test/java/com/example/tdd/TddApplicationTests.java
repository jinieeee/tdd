package com.example.tdd;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TddApplicationTests {

	@Test
	public void testMultipleication() {
		Dollar five = new Dollar(5);
		five.times(2);
		assertEquals(10, five.amount);
	}
}

class Dollar {
	int amount = 10;

	Dollar(int amount) {
		this.amount = amount;
	}

	void times(int multiplier) {
		amount *= multiplier;
	}
}
