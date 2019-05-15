package ex4.main;

/**
 * 
 * @author Pascal Gepperth (4005085)
 *
 */
public class Methods {

	// 4.3 a)
	public static boolean isEqual(int[] firstArray, int[] secondArray) {
		if (firstArray.length != secondArray.length) {
			return false;
		}
		for (int i = 0; i < firstArray.length; i++) {
			if (firstArray[i] != secondArray[i]) {
				return false;
			}
		}
		return true;
	}

	// 4.3 b)
	public static void reverse(int[] array) {
		int temp;
		int n = array.length;
		for (int i = 0; i < n / 2; i++) {
			temp = array[i];
			array[i] = array[n - 1 - i];
			array[n - 1 - i] = temp;
		}
	}

	// 4.3 c)
	public static boolean isReversed(int[] firstArray, int[] secondArray) {
		if (firstArray.length != secondArray.length) {
			return false;
		}
		for (int i = 0; i < firstArray.length; i++) {
			if (firstArray[i] != secondArray[secondArray.length - 1 - i]) {
				return false;
			}
		}
		return true;
	}

	// 4.3 d)
	public static boolean isPrime(int number) {
		if (number <= 2) {
			return number == 2;
		}
//		for (int i = 2; i < number; i++) {
//			if ((number % i) == 0) {
//				return false;
//			}
//		}

		/*
		 * Check divider d for d^2 < number.
		 * 
		 * In case of n is no prime, it is n = d1 * d2 with d1 <= sqrt(n). So it is not
		 * necessary to check for divider greater than sqrt(n).
		 * 
		 * Check small divider ( 2 <= d <= 7 ) where it is only needed to check for
		 * prime divider.
		 * 
		 * Check greater divider where it is only needed to check for divider ending by
		 * 1, 3, 7 and 9. Every divider ending by 0, 2, 4, 5, 6 and 8 is already
		 * checked. (dividers are multiple of small divider). Even endings can be
		 * divided by 2, ending 5 can be divided by 5.
		 */
		if (number % 2 == 0) {
			return false;
		}
		if (number % 3 == 0) {
			return number == 3;
		}
		if (number % 5 == 0) {
			return number == 5;
		}
		if (number % 7 == 0) {
			return number == 7;
		}

		for (int i = 1; i * i * 100 <= number; i++) {
			// divider ending by 1
			if (number % (i * 10 + 1) == 0) {
				return false;
			}
			// divider ending by 3
			if (number % (i * 10 + 3) == 0) {
				return false;
			}
			// divider ending by 7
			if (number % (i * 10 + 7) == 0) {
				return false;
			}
			// divider ending by 9
			if (number % (i * 10 + 9) == 0) {
				return false;
			}
		}
		return true;
	}

	// 4.3 e)
	public static boolean belongsToPrimeTwin(int number) {
		return (isPrime(number) && (isPrime(number - 2) || isPrime(number + 2)));
	}

	public static void main(String[] args) {
		// task 4.1 a)
		// System.out.println(Arrays.toString(array)) might be helpful to debug your
		// code

		// task 4.1 b)
		// task 4.1 c)

		// task 4.1 d)
		// task 4.1 e)
	}
}
