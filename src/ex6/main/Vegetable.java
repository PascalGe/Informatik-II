package ex6.main;

public class Vegetable extends Food {

	private int cooked;
	private int cookingMinutes;

	public Vegetable(String _name, int _kcal, int cookingMinutes) {
		super(_name, _kcal);
		this.cookingMinutes = cookingMinutes;
		cooked = 0;
	}

	@Override
	public int prepare(int weight) {
		while (!edible) {
			cookIt();
		}
		return weight;
	}

	private void cookIt() {
		cooked++;
		if (cooked == cookingMinutes) {
			edible = true;
		}
	}

}
