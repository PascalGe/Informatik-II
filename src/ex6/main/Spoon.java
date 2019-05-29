package ex6.main;

/**
 * 
 * @author Pascal Gepperth (4005085)
 *
 */
public class Spoon extends Cutlery {

	/**
	 * Creates a new spoon default size and dirtReduction .2.
	 * 
	 * @param dirty - dirtiness of the spoon.
	 */
	public Spoon(float f) {
		super(f);
		dirtReduction = .2f;
	}

}
