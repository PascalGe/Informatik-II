package ex6.main;

/**
 * 
 * @author Pascal Gepperth (4005085)
 *
 */
public abstract class Food {

	public String name;
	public int kcal; // per 100g
	protected boolean edible;

	public abstract int prepare(int weight);

	/**
	 * Creates a new food.
	 * 
	 * @param _name - name of the food.
	 * @param _kcal - calories per 100g.
	 */
	public Food(String _name, int _kcal) {
		name = _name;
		kcal = _kcal;
		edible = false;
	}

	/**
	 * Computes the calories of the food.
	 * 
	 * @param gramms - the weight of the food.
	 * @return the calories of the food, if edible. Zero else.
	 */
	public float computeCalories(float gramms) {
		if (!edible) {
			return 0.0f;
		}
		return (kcal / 100f) * gramms;
	}
}
