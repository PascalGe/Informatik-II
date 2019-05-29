package ex6.main;

public class Meal {

	private float kcalSum;
	private String[] ingredients; // ingredient names
	private int[] amounts; // weights of ingredients
	private int ingredientCount; // number of ingredients that were added

	public Meal() {
		kcalSum = 0;
		ingredientCount = 0;
		ingredients = new String[100];
		amounts = new int[100];
	}

	// TODO: implement an addFood() method
	// to collect names, weights and accumulate kCals
	/**
	 * Adds food at its current status.
	 * 
	 * @param food   - the food that's to be added.
	 * @param weight - the weight of the added food.
	 */
	public void addFood(Food food, int weight) {
		ingredients[ingredientCount] = food.name;
		amounts[ingredientCount] = weight;
		kcalSum += food.computeCalories(weight);
		ingredientCount++;
	}

	public void printStatus() {
		System.out.println("Your meal has: " + kcalSum + "kcal.");
		for (int i = 0; i < ingredientCount; i++) {
			System.out.println(amounts[i] + "g of " + ingredients[i]);
		}
	}

}
