package ex5.main;

public class Meal {

    // TODO 5.1 a) kcalSum
	private float kcalSum;

    // TODO 5.1 j) ingredients, amounts
    // - Think about how to keep track of how many ingredients were added!

    public Meal() {
	// TODO a) initialize
    	this.kcalSum = 0;
    }

    public void addFruit(Fruit fruit, int weight) {
    	kcalSum += fruit.computeCalories(weight);
    	// TODO 5.1j) Add information to ingredients and amounts.
    }

    /* TODO 5.1 c) Remove this comment after implementing the Vegetable class.
    public void addVegetable(Vegetable vegetable) {
	kcalSum += vegetable.computeCalories(gVegetable);
	// TODO 5.1j) Add information to ingredients and amounts.
    }
    */

    public void printStatus() {
	/*
	 * TODO 5.1 a) Remove this comment after creating the attribute kcalSum.
	 * System.out.println("Your meal has: " + kcalSum + "kcal.");
	 */
	// TODO 5.1 j) Print a list of ingredients.
    }

}
