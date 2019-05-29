package ex6.main;
/**
 * 
 * @author Pascal Gepperth (4005085)
 *
 */
public class Fork extends Cutlery {

	/**
	 * Creates a new Fork with default size and dirtReduction of .1.
	 * @param dirty - the dirtiness of the Fork.
	 */
	public Fork(float dirty) {
		super(dirty);
		dirtReduction = .1f;
	}

}
