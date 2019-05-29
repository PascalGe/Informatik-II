package ex6.main;

/**
 * 
 * @author Pascal Gepperth (4005085)
 *
 */
public class Knife extends Cutlery {

	/**
	 * Creates a new knife with default size and dirtReduction of .4.
	 * 
	 * @param dirty - the dirtiness of the knife.
	 */
	public Knife(float dirty) {
		super(dirty);
		dirtReduction = .4f;
	}

}
