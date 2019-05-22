package ex5.main;

public class Meal {

	private float kcalSum;
	// counter
	private int numberOfIngredients;

	private String[] ingredients;
	private int[] amounts;

	public Meal() {
		// initialize
		kcalSum = 0;
		numberOfIngredients = 0;
		ingredients = new String[100];
		amounts = new int[100];
	}

	public void addFruit(Fruit fruit, int weight) {
		// compute calories
		kcalSum += fruit.computeCalories(weight);
		// store ingredient
		ingredients[numberOfIngredients] = fruit.name;
		// store amount
		amounts[numberOfIngredients] = weight;
		// increment counter
		numberOfIngredients++;
	}

	public void addVegetable(Vegetable vegetable, int weight) {
		// compute calories
		kcalSum += vegetable.computeCalories(weight);
		// store ingredient
		ingredients[numberOfIngredients] = vegetable.name;
		// store amount
		amounts[numberOfIngredients] = weight;
		// increment counter
		numberOfIngredients++;
	}

	public void printStatus() {
		System.out.println("Your meal has: " + kcalSum + "kcal.");
		// Print a list of ingredients.
		for (int i = 0; i < numberOfIngredients; i++) {
			System.out.println(amounts[i] + "g of " + ingredients[i]);
		}
	}
}