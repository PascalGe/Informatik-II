package ero.test;

import ero.util.InsertionSort;

public class InsertionSortDemo {

	public static void main(String[] args) {

		int[] array = { 5, 7, 3, -6, 12, 2, 9 - 1 };
		System.out.println("Unsortiert:");
		printArray(array);
		InsertionSort.sort(array);
		System.out.println("Sortiert:");
		printArray(array);

	}

	private static void printArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.print("\n");
	}

}
