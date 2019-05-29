package ex6.main;

/**
 * 
 * @author Pascal Gepperth (4005085)
 *
 */
public class Fruit extends Food {

	private boolean hasPeel;

	/**
	 * Creates a new fruit.
	 * 
	 * @param _name    - name of the fruit.
	 * @param _kcal    - calories per 100g.
	 * @param _hasPeel - true, if the fruit has peel.
	 */
	public Fruit(String _name, int _kcal, boolean _hasPeel) {
		super(_name, _kcal);
		hasPeel = _hasPeel;
		if (!hasPeel) {
			edible = true;
		}
	}

	/**
	 * Prepares the fruit to be eaten.
	 * 
	 * @param weight - the weight of the fruit.
	 * @return the weight of the prepared food.
	 */
	public int prepare(int weight) {
		if (hasPeel) {
			hasPeel = false;	// should change?
			edible = true;
			weight *= .9;
		}
		return weight;
	}
}