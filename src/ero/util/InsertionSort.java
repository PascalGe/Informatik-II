package ero.util;

public class InsertionSort {

	// in-place
	public static void sort(int[] array) {

		if (array == null || array.length < 2) {
			return;
		}
		// iterate array
		for (int i = 1; i < array.length; i++) {
			int currentValue = array[i];
			for (int currentIndex = i; currentIndex > 0 && array[currentIndex - 1] > currentValue; currentIndex--) {
				// swap
				array[currentIndex] = array[currentIndex - 1];
				array[currentIndex - 1] = currentValue;
			}
		}
	}
}