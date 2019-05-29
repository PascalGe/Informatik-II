package ex6.main;

/**
 * 
 * @author Pascal Gepperth (4005085)
 *
 */
public class Pot extends Dish {
	/**
	 * Creates a new pot with size 5 and default dirtReduction.
	 * 
	 * @param dirty - dirtiness of the pot.
	 */
	public Pot(float dirty) {
		super(dirty);
		size = 5;
	}

}
