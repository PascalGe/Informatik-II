package ex3.main;

public class Array2DExercise {
	// 3.2 a)
	public static void print_array_2d(int[] array) {
		// TODO fill me
		int width = (int) Math.sqrt(array.length);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(array[width * i + j]);
			}
			System.out.print("\n");
		}
	}

	// 3.2 b)
	public static int[] walk(int[] labyrinth) {

		int width = (int) Math.sqrt(labyrinth.length);

		// right --> +1, down --> +width
		boolean right = true;

		// start at [0,0]
		int i = 0;

		while (true) {
			// mark path
			labyrinth[i] = 1;

			// if next step goes right
			if (right) {
				// if next step out of array
				if ((i + 1) % width == 0) {
					return labyrinth;
				}
				// if next field blocked
				if (labyrinth[i + 1] == 8) {
					right = false;
				} else {
					// go right
					i++;
				}
			} else {
				// if next step out of array
				if ((i + width) / width >= width) {
					return labyrinth;
				}
				// if next field blocked
				if (labyrinth[i + width] == 8) {
					right = true;
				} else {
					// go down
					i += width;
				}
			}
		}
	}

	public static void main(String[] args) {
		{
			int[] array = new int[5 * 5];
			array[0 * 5 + 2] = 8;
			array[2 * 5 + 1] = 8;
			print_array_2d(array);
			System.out.print("\n");
			print_array_2d(walk(array));
		}
		System.out.print("\n\n--------------------\n\n");
		{
			int[] array = new int[5 * 5];
			array[0 * 5 + 2] = 8;
			array[2 * 5 + 1] = 8;
			array[1 * 5 + 3] = 8;
			array[4 * 5 + 2] = 8;
			array[3 * 5 + 4] = 8;
			print_array_2d(array);
			System.out.print("\n");
			print_array_2d(walk(array));
		}
		System.out.print("\n\n--------------------\n\n");

	}
}
