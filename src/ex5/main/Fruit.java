package ex5.main;

public class Fruit {

	public String name;
	public int kcal; // per 100g
	private boolean hasPeel;

	public Fruit(String _name, int _kcal, boolean _hasPeel) {
		name = _name;
		kcal = _kcal;
		hasPeel = _hasPeel;
	}

	public float computeCalories(float gramms) {
		// Fruits with peal contribute only 90% of their mass to the kcal.
		if (hasPeel) {
			gramms *= .9f;
		}
		return (this.kcal / 100.0f) * gramms;
	}
}