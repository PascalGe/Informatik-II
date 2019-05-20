package ero.util;

public class InsertionSort {

	public static void sort(int[] array) {

		if (array == null || array.length < 2) {
			return;
		}
		// iterate array
		for (int i = 1; i < array.length; i++) {
			int currentValue = array[i];
			for (int newIndex = i; newIndex > 0 && array[newIndex - 1] > currentValue; newIndex--) {
				// swap
				array[newIndex] = array[newIndex - 1];
				array[newIndex - 1] = currentValue;
			}
		}
	}
}