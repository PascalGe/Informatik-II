package ex4.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ex4.main.Methods;

class MethodsTest {

	@Test
	void testIsEqual() {
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8 };
		int[] compare1 = { 1, 2, 3, 4, 5, 6, 7, 8 };
		int[] compare2 = { 1, 2, 3, 4, 5, 7, 7, 8 };
		int[] compare3 = { 1, 2, 3, 4, 5, 6, 7 };

		assertTrue(Methods.isEqual(array, compare1));
		assertFalse(Methods.isEqual(array, compare2));
		assertFalse(Methods.isEqual(array, compare3));
	}

	@Test
	void testReserve() {
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8 };
		int[] compare1 = { 8, 7, 6, 5, 4, 3, 2, 1 };

		Methods.reverse(array);

		assertArrayEquals(compare1, array);
	}

	@Test
	void testIsReserved() {
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8 };
		int[] compare1 = { 1, 2, 3, 4, 5, 6, 7, 8 };
		int[] compare2 = { 2, 1, 3, 4, 5, 6, 7, 8 };
		int[] compare3 = { 2, 1, 3, 4, 5, 6, 7 };

		assertTrue(Methods.isEqual(array, compare1));
		assertFalse(Methods.isEqual(array, compare2));
		assertFalse(Methods.isEqual(array, compare3));
	}

	@Test
	void testIsPrime() {
		int n1 = 9;
		int n2 = 10;
		int n3 = 11;
		int n4 = 13;

		assertFalse(Methods.isPrime(n1));
		assertFalse(Methods.isPrime(n2));
		assertTrue(Methods.isPrime(n3));
		assertTrue(Methods.isPrime(n4));
	}

	@Test
	void testBelongToPrimeTwin() {
		int n1 = 9;
		int n2 = 10;
		int n3 = 11;
		int n4 = 13;

		assertFalse(Methods.belongsToPrimeTwin(n1));
		assertFalse(Methods.belongsToPrimeTwin(n2));
		assertTrue(Methods.belongsToPrimeTwin(n3));
		assertTrue(Methods.belongsToPrimeTwin(n4));
	}
}
