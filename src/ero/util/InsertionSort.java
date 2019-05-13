package ero.util;

public class InsertionSort {

	public static int[] sort(int[] array) {

		if (array == null || array.length < 2) {
			return array;
		}
		// iterate array
		nextValue: for (int i = 1; i < array.length; i++) {
			int currentValue = array[i];
			for (int newIndex = i; newIndex >= 0; newIndex--) {
				// if previous value is less than currentValue
				if (newIndex == 0 || array[newIndex - 1] <= currentValue) {
					array[newIndex] = currentValue;
					continue nextValue;
				}
				// else shift right
				else {
					array[newIndex] = array[newIndex - 1];
				}
			}
		}
		return array;
	}
}