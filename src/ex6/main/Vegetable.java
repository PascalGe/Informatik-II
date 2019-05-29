package ex6.main;

/**
 * 
 * @author Pascal Gepperth (4005085)
 *
 */
public class Vegetable extends Food {

	private int cooked;
	private int cookingMinutes;

	/**
	 * Creates a new vegetable.
	 * 
	 * @param _name          - name of the vegetable.
	 * @param _kcal          - calories per 100g.
	 * @param cookingMinutes - time that the vegetable has to be cooked, before its
	 *                       edible.
	 */
	public Vegetable(String _name, int _kcal, int cookingMinutes) {
		super(_name, _kcal);
		this.cookingMinutes = cookingMinutes;
		cooked = 0;
	}

	/**
	 * Prepares the vegetable to be eaten.
	 * 
	 */
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