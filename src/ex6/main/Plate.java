package ex6.main;

/**
 * 
 * @author Pascal Gepperth (4005085)
 *
 */
public class Plate extends Dish {

	/**
	 * Creates a new Plate with size 2 and default dirtReduction.
	 * 
	 * @param dirty - dirtiness of the Plate.
	 */
	public Plate(float dirty) {
		super(dirty);
		size = 2;
	}

}
