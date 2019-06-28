package ex9.main2;

/**
 * 
 * @author Pascal Gepperth (4005085)
 *
 */
public class EmptyTree implements SortedFloatTree {

	public EmptyTree() {
	}

	@Override
	public String asString() {
		return "";
	}

	@Override
	public int depth() {
		return 0;
	}

	@Override
	public int nodeCount() {
		return 0;
	}

	@Override
	public boolean exists(float element) {
		return false;
	}

	@Override
	public SortedFloatTree insert(float element) {
		// TODO
		return new Node(element, new EmptyTree(), new EmptyTree());
	}

	@Override
	public SortedFloatTree getClone() {
		return new EmptyTree();
	}
}