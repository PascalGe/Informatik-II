package ex6.main;

/**
 * 
 * @author Pascal Gepperth (4005085)
 * 
 */
public abstract class Dish {

	public int size;
	public float dirtReduction = .3f;
	public float dirty;

	/**
	 * Creates a new Dish
	 * 
	 * @param dirty - the value of dirtiness.
	 */
	public Dish(float dirty) {
		this.dirty = dirty;
	}

	/**
	 * Prints the current status of the dish.
	 */
	public void printStatus() {
		if (dirty > .1) {
			System.out.println(getClass().getSimpleName() + " still dirty");
		} else {
			System.out.println(getClass().getSimpleName() + " is clean");
		}

	}

	/**
	 * Cleans the dish.
	 */
	public void clean() {
		if (dirty < dirtReduction) {
			dirty = 0;
		} else {
			dirty -= dirtReduction;
		}
	}
}