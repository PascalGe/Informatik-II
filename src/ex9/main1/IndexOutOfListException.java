package ex9.main1;

public class IndexOutOfListException extends Exception {

	private static final long serialVersionUID = 770338293522794496L;

	public IndexOutOfListException() {
		super("The index is not reachable for the list.");
	}
}