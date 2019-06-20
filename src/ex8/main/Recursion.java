package ex8.main;

import java.util.Arrays;

/**
 * 
 * @author Pascal Gepperth (4005085)
 *
 */
public class Recursion {

	/**
	 * Computes the n-th Fibonacci number recursively.
	 * 
	 * @param n - count of Fibonacci number.
	 * @return - Fibonacci number.
	 */
	public static int fibonacci(int n) {
		// TODO 8.1.a) compute the n-th Fibonacci number (recursively!)
		if (n == 1 || n == 0) {
			return n;
		}
		return fibonacci(n - 1) + fibonacci(n - 2);
	}

	/**
	 * Computes the Gregory-Leibniz-Sequence recursively.
	 * 
	 * @param n - max. element of the sequence.
	 * @return approximation to pi.
	 */
	public static double gregoryLeibnizRecursive(int n) {
		return (n > 0 ? gregoryLeibnizRecursive(n - 1) : 0) + 4.0 / (n * 2 + 1) * (1 - 2 * (n % 2));
	}

	/**
	 * Computes the Gregory-Leibniz-Sequence iteratively.
	 * 
	 * @param n - max. element of the sequence.
	 * @return approximation to pi.
	 */
	public static double gregoryLeibnizIterative(int n) {
		// TODO 8.1.b) implement an iterative version of the Gregory-Leibniz series
		double pi = 0.0;
		for (int i = 0; i <= n; i++) {
			pi += Math.pow(-1, i) / (2 * i + 1);
		}
		return 4 * pi;
	}

	public static void main(String[] args) {

		int[] fibs = new int[20];
		for (int i = 0; i < fibs.length; i++) {
			fibs[i] = fibonacci(i);
		}
		System.out.println("Your first 20 Fibonacci numbers:");
		System.out.println(Arrays.toString(fibs));

		System.out.println("\nApproximations of Pi (" + Math.PI + "):");
		System.out.println("Recursive: " + gregoryLeibnizRecursive(10000));
		System.out.println("Iterative: " + gregoryLeibnizIterative(10000000));
	}
}
