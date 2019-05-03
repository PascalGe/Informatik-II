package ex2.main;

/**
 * 
 * @author Pascal Gepperth (4005085)
 *
 */
public class Series {

	// Exercise Task
	// 2.1 a)
	public static int sum_up() {
		int result = 0;

		for (int i = 1; i <= 1000; i++) {
			result += i;
		}

		return result;
	}

	// Exercise Task
	// 2.1 b)
	public static void multiplication_table(int n) {

		for (int i = 1; i <= 10; i++) {
			System.out.println(n + " x " + i + " = " + i * n);
		}
	}

	// Exercise Task
	// 2.1 c)
	public static void fizz_buzz() {
		for (int i = 1; i <= 100; i++) {
			String s = "";

			if (i % 3 == 0) {
				s += "Fizz";
			}
			if (i % 5 == 0) {
				s += "Buzz";
			}
			if (!s.isEmpty()) {
				System.out.println(s);
			} else {
				System.out.println(i);
			}
		}
	}

	// Exercise Task
	// 2.1 d)
	public static void chess_board(int n) {
		if (n < 0) {
			return;
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 2 * n; j++) {
				if ((i + j) % 2 == 0) {
					System.out.print("#");
				} else {
					System.out.print("-");
				}
			}
			System.out.print("\n");
		}
	}

	// Exercise Task
	// 2.1 e)
	public static int factorial(int n) {
		if (n < 0) {
			return 0;
		}
		int result = 0;

		for (int i = n; i > 1; i--) {
			result *= i;
		}
		return result;
	}

	// Exercise Task
	// 2.1 f)
	public static double ln2(int n) {
		double result = 0;
		for (int i = 1; i <= n; i++) {
			result += Math.pow(-1, i - 1) / i;
		}
		return result;
	}

	// Exercise Task
	// 2.1 g)
	public static void reverse_digits(int n) {
		while (n > 0) {
			System.out.println(n % 10);
			n /= 10;
		}
	}

	// Exercise Task
	// 2.1 h)
	public static double leibniz_series(int n) {
		double res = 0;

		for (int k = 0; k <= n; k++) {
			res += Math.pow(-1, k) / (2 * k + 1);
		}
		return 4 * res;
	}

	// Exercise Task
	// 2.1 i)
	public static boolean palindrome(String str) {
		int n = str.length();
		for (int i = 0; i < n / 2; i++) {
			// Check character from both sides
			if (str.charAt(i) != str.charAt(n - 1 - i)) {
				// Return false if they doesn't match
				return false;
			}
		}
		// Otherwise return true
		return true;
	}

	// Exercise Task
	// 2.1 j)
	public static long max_collatz(long m) {
//		if (m < 1) {
//			return 0;
//		}
		long max_startwert = 0; // 1
		long max_steps = 0;

		for (long i = 1; i <= m; i++) {
			// Calculate steps for i
			long steps = collatz(i);
			if (steps > max_steps) {
				max_startwert = i;
				max_steps = steps;
			}
		}
		return max_startwert;
	}

	private static long collatz(long a) {
		if (a == 1) {
			return 0;
		}
		// Set next number in sequence
		if (a % 2 == 0) {
			a /= 2;
		} else {
			a = 3 * a + 1;
		}
		// Get steps for next number in sequence
		// Return increased steps
		return collatz(a) + 1;
	}

	// Exercise Task
	// 2.1 k)
	public static double gradient_descent(double x0, double a, double b) {
		double x_cur = x0;
		double x_old = x0 + 1;

		int counter = 0;

		while (counter < 1000 && Math.abs(x_cur - x_old) > 10e-7) {
			x_old = x_cur;
			x_cur = x_old - 0.001 * 2 * (x_old - a);
			counter++;
		}

		// Calculate minimum -- should be returned, shouldn't it?
//		double min = Math.pow(x_cur - a, 2) + b;

		return x_cur;
	}

	// Exercise Task
	// 2.1 l)
	public static void pattern_a(int num_rows) {
		for (int i = 1; i <= num_rows; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.print(i + ",");
			}
			System.out.print("\n");
		}
	}

	// Exercise Task
	// 2.1 m)
	public static void pattern_b(int num_rows) {
		for (int i = 1; i <= num_rows; i++) {
			for (int j = i; j >= 1; j--) {
				System.out.print(j + ",");
			}
			System.out.print("\n");
		}

	}

	// Exercise Task
	// 2.1 n)
	public static int seven_sum(int num) {
		int sum = 0;
		int summand = 0;
		for (int i = 0, multiplier = 1; i < num; i++, multiplier *= 10) {
			summand += 7 * multiplier;
			sum += summand;
		}

		return sum;
	}

	// Exercise Task
	// 2.1 o)
	public static void ones_and_zeros(int rows) {
		for (int i = 1; i <= rows; i++) {
			for (int j = 1; j <= i; j++) {
				int digit = (i + j) % 2;
				System.out.print(digit + ",");
			}
			System.out.print("\n");
		}
	}

	// Exercise Task
	// 2.2 p)
	public static boolean is_perfect(int number) {
		int sum = 0;
		for (int i = 1; i < number; i++) {
			// Check i for divider
			if (number % i == 0) {
				// Sum up divider
				sum += i;
			}
		}

		return sum == number;
	}

	public static void main(String[] args) {

		System.out.println("2.1 a) ");
		System.out.println(sum_up());

		System.out.println("2.1 b) ");
		multiplication_table(4);

		System.out.println("2.1 c) ");
		fizz_buzz();

		System.out.println("2.1 d) ");
		chess_board(4);

		System.out.println("2.1 e) ");
		System.out.println(factorial(4));

		System.out.println("2.1 f) ");
		System.out.println(ln2(100000));

		System.out.println("2.1 g) ");
		reverse_digits(1239);

		System.out.println("2.1 h) ");
		System.out.println(leibniz_series(1000));

		System.out.println("2.1 i) ");
		System.out.println(palindrome("1"));
		System.out.println(palindrome("12321"));
		System.out.println(palindrome("12323"));

		System.out.println("2.1 j) ");
		System.out.println(max_collatz(100));

		System.out.println("2.1 k) ");
		System.out.println(gradient_descent(1, 10, 2));

		System.out.println("2.1 l) ");
		pattern_a(5);

		System.out.println("2.1 m) ");
		pattern_b(5);

		System.out.println("2.1 n) ");
		System.out.println(seven_sum(1)); // 7 = 7
		System.out.println(seven_sum(2)); // 7 + 77 = 84
		System.out.println(seven_sum(3)); // 7 + 77 + 777 = 861

		System.out.println("2.1 o) ");
		ones_and_zeros(4);

		System.out.println("2.1 p) ");
		System.out.println(is_perfect(6));
		System.out.println(is_perfect(14));
	}
}
