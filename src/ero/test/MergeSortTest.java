package ero.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ero.util.MergeSort;
import junit.framework.TestCase;

class MergeSortTest extends TestCase {
	private static final int n = (int)1e6;

	@Override
	public void setUp() throws Exception {
		
	}

	@Test
	void test1() {
		int[] actual = { 5, 1, 6, 2, 3, 4 };
		int[] expected = { 1, 2, 3, 4, 5, 6 };
		assertArrayEquals(MergeSort.mergeSort(actual), expected);
	}

	@Test
	void test2() {
		numberTest(1);
	}

	@Test
	void test3() {
		numberTest(2);
	}
	
	@Test
	void test4() {
		numberTest(4);
	}
	
	@Test
	void test5() {
		numberTest(8);
	}
	
	@Test
	void test6() {
		numberTest(16);
	}
	
	private void numberTest(int multiplier) {
		
		int[] actual = new int[multiplier*n], expected = new int[multiplier*n];

		for (int i = 0; i < multiplier*n; i++) {
			actual[i] = multiplier*n - 1 - i;
			expected[i] = i;
		}
		assertArrayEquals(MergeSort.mergeSort(actual), expected);
	}

}
