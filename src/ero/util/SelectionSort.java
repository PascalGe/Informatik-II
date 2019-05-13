package ero.util;

public class SelectionSort {

	public static int[] sort(int[] array) {
		if (array == null || array.length < 2) {
			return array;
		}
		for (int i = 0; i < array.length; i++) {
			int changeIndex = i;

			// select smallest item
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] < array[changeIndex]) {
					changeIndex = j;
				}
			}

			// change items
			int temp = array[i];
			array[i] = array[changeIndex];
			array[changeIndex] = temp;
		}
		return array;
	}
}