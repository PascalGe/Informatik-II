package ex9.main1;

/**
 * 
 * @author Pascal Gepperth (4005085)
 *
 */
public class EmptyList implements IntegerList {

	@Override
	public void print() {
		System.out.print("\n");
	}

	@Override
	public int length() {
		return 0;
	}

	@Override
	public IntegerList append(int element) {
		return new Pair(element);
	}

	@Override
	public int get(int index) throws Exception {
		// TODO define Exceptions?
		throw new IndexOutOfListException();
	}

	@Override
	public IntegerList insert(int element, int index) throws Exception {
		if (index == 0) {
			return new Pair(element);
		}
		throw new EmptyListInsertException();
	}

	@Override
	public IntegerList deleteElement(int index) throws Exception {
		throw new IndexOutOfListException();
	}

	@Override
	public IntegerList reversed() {
		return this;
	}

	@Override
	public IntegerList extend(IntegerList other) {
		IntegerList list = this;
		for (int i = 0; i < other.length(); i++) {
			try {
				list = list.append(other.get(i));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public int min() {
		// TODO min of EmptyList?
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isSorted() {
		return true;
	}

	@Override
	public IntegerList getClone() {
		return new EmptyList();
	}
}
