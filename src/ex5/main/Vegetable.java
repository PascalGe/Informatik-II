package ex5.main;
/**
 * 
 * @author Pascal Gepperth (4005085)
 *
 */
public class Vegetable {

	public String name;
	public int kcal;
	private boolean edible;
	private int cookingMinutes;
	private int cooked;

	public Vegetable(String name, int kcal, int cookingMinutes) {
		this.name = name;
		this.kcal = kcal;
		this.edible = false;
		this.cookingMinutes = cookingMinutes;
		cooked = 0;
	}

	public boolean isEdible() {
		return edible;
	}

	public void cookIt() {
		cooked++;
		if (cooked >= cookingMinutes) {
			edible = true;
		}
	}

	public float computeCalories(float gramms) {
		if (!edible) {
			return 0;
		}
		return (kcal / 100.0f) * gramms;
	}
}