package ex2.main;

/**
 * 
 * @author Pascal Gepperth (4005085)
 *
 */
public class Imprecision {
	// Exercise Task
	// 2.2 a)
	public static float different_same_sum(float x) {

		float forward_sum = 0;
		float backward_sum = 0;

		for (int n = 1; n <= 100; n++) {
			forward_sum += (float) Math.pow(-1, n + 1) * (float) Math.pow(x - 1, n) / n;
			backward_sum += (float) Math.pow(-1, 100 - n + 2) * (float) Math.pow(x - 1, 100 - n + 1) / (100 - n + 1);
		}
		return forward_sum - backward_sum;
		/*
		 * Diese Abweichung ergibt sich aus der Begrenzten Genauigkeit der Darstellung
		 * der Mantisse
		 */
	}

	// Exercise Task
	// 2.2 b)
	public static double double_eval_polynom(double x0, double y0) {
		double res = 0;

		res += 333.75d * Math.pow(y0, 6);
		res += Math.pow(x0, 2)
				* (11d * Math.pow(y0, 2) * Math.pow(x0, 2) - Math.pow(y0, 6) - 121d * Math.pow(y0, 4) - 2d);
		res += 5.5d * Math.pow(y0, 8);
		res += x0 / (2d * y0);

		// best value
//		res += 333.75d * Math.pow(y0, 6);
//		res += 11d * Math.pow(x0, 4) * Math.pow(y0, 2);
//		res -= Math.pow(y0, 6) * Math.pow(x0, 2);
//		res -= 121d * Math.pow(y0, 4) * Math.pow(x0, 2);
//		res -= 2d * Math.pow(x0, 2);
//		res += 5.5d * Math.pow(y0, 8);
//		res += x0 / (2d * y0);

		return res;
	}

	// Exercise Task
	// 2.2 b) Part 2
	public static float float_eval_polynom(float x0, float y0) {
		float res = 0;

		res += 333.75f * (float) Math.pow(y0, 6);
		res += (float) Math.pow(x0, 2) * (11f * (float) Math.pow(y0, 2) * (float) Math.pow(x0, 2)
				- (float) Math.pow(y0, 6) - 121f * (float) Math.pow(y0, 4) - 2f);
		res += 5.5f * (float) Math.pow(y0, 8);
		res += x0 / (2f * y0);

		// best value
//		res -= (float) Math.pow(y0, 6) * (float) Math.pow(x0, 2);
//		res += 11f * (float) Math.pow(x0, 4) * (float) Math.pow(y0, 2);
//		res += 333.75f * (float) Math.pow(y0, 6);
//		res -= 121f * (float) Math.pow(y0, 4) * (float) Math.pow(x0, 2);
//		res -= 2f * (float) Math.pow(x0, 2);
//		res += 5.5f * (float) Math.pow(y0, 8);
//		res += x0 / (2f * y0);

		return res;
	}

	public static void main(String[] args) {
		System.out.println("2.2 a) ");
		System.out.println(different_same_sum(2.97f));
		System.out.println("2.2 b) ");
		System.out.println(double_eval_polynom(77617, 33096)); // 1, 1
		System.out.println(float_eval_polynom(77617, 33096)); // 1, 1
	}
}
