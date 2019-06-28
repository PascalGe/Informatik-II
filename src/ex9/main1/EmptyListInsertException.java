package ex9.main1;

public class EmptyListInsertException extends Exception {

	private static final long serialVersionUID = 5916811963299292395L;

	public EmptyListInsertException() {
		super("Can't insert element. No previous index specified."); // Can't insert an element into an EmptyList.
	}
}