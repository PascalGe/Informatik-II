package ero.util;

public class MergeSort {

	public static int[] mergeSort(int[] array) {
		// Stop if there's nothing to be sort
		if (array.length < 2) {
			return array;
		}
		// Divide the array
		// Generate new arrays
		int mid = array.length / 2;
		int[] firstArray = new int[mid];
		int[] secondArray = new int[array.length - mid];

		// Fill arrays
		for (int i = 0; i < firstArray.length; i++) {
			firstArray[i] = array[i];
		}
		for (int i = 0; i < secondArray.length; i++) {
			secondArray[i] = array[i + mid];
		}

		// Merge arrays
		return merge(mergeSort(firstArray), mergeSort(secondArray));
	}

	private static int[] merge(int[] firstArray, int[] secondArray) {
		int[] sortArray = new int[firstArray.length + secondArray.length];
		int i = 0, j = 0;
		while (i < firstArray.length && j < secondArray.length) {
			if (firstArray[i] <= secondArray[j]) {
				sortArray[i+j] = firstArray[i++];
			} else {
				sortArray[i+j] = secondArray[j++];
			}
		}
		while (i< firstArray.length) {
			sortArray[i+j] = firstArray[i++];
		}
		while (j< secondArray.length) {
			sortArray[i+j] = secondArray[j++];
		}
		return sortArray;
	}
}
