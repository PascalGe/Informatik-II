package ex5.main;

/**
 * 
 * @author Pascal Gepperth (4005085)
 *
 */
public class Dishwasher {

	private static final int MAX_CAPACITY = 20;

	// set dishwasher
	private static int fullness = 0;
	private static int counter = 0;
	private static Dish[] dishwasher = new Dish[MAX_CAPACITY];

	public static void main(String[] args) {

		// Create dishes
		Dish[] dishes = new Dish[10];
		dishes[0] = new Dish("Cutlery", 0.1f);
		dishes[1] = new Dish("Cutlery", 0.1f);
		dishes[2] = new Dish("Plate", 0.6f);
		dishes[3] = new Dish("Plate", 0.2f);
		dishes[4] = new Dish("Cup", 0.2f);
		dishes[5] = new Dish("Cup", 0.8f);
		dishes[6] = new Dish("Plate", 0.1f);
		dishes[7] = new Dish("Pot", 0.9f);
		dishes[8] = new Dish("Pot", 0.4f);
		dishes[9] = new Dish("Plate", 0.4f);

		/*
		 * fill dishwasher while dishwasher is not full and dishes left
		 */
		for (int i = 0; i < dishes.length && fullness < MAX_CAPACITY; i++) {
			// if dish fits
			if (fullness + dishes[i].getSize() <= MAX_CAPACITY) {
				add(dishes[i]);
			}
			// else skip current dish
		}

		clean();
		empty();

		for (int i = 0; i < dishes.length; i++) {
			dishes[i].printStatus();
		}
	}

	private static void clean() {
		for (int i = 0; i < counter; i++) {
			dishwasher[i].clean(0.3f);
		}

	}

	private static void empty() {
		for (int i = 0; i < counter; i++) {
			dishwasher[i] = null;
		}
		counter = 0;
	}

	private static void add(Dish dish) {
		dishwasher[counter] = dish;
		fullness += dish.getSize();
		counter++;
	}
}