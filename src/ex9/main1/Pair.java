package ex9.main1;

/**
 * 
 * @author Pascal Gepperth (4005085)
 *
 */
public class Pair implements IntegerList {

	private int element;

	private IntegerList rest;

	public Pair(int element) {
		this(element, new EmptyList());
	}

	public Pair(int element, IntegerList rest) {
		this.element = element;
		this.rest = rest;
	}

	@Override
	public void print() {
		System.out.print(element + ", ");
		rest.print();
	}

	@Override
	public int length() {
		return rest.length() + 1;
	}

	@Override
	public IntegerList append(int element) {
		try {
			return insert(element, length());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int get(int index) throws Exception {
		return index == 0 ? element : rest.get(index - 1);
	}

	@Override
	public IntegerList insert(int element, int index) throws Exception {
		if (index == 0) {
			return new Pair(element, getClone());
		}
		return new Pair(this.element, rest.insert(element, index - 1));
	}

	@Override
	public IntegerList deleteElement(int index) throws Exception {
		if (index == 0) {
			return rest.getClone();
		} else {
			return new Pair(this.element, rest.deleteElement(index - 1));
		}
	}

	@Override
	public IntegerList reversed() {
		int length = length();
		try {
			Pair head = new Pair(element);
			for (int i = 1; i < length; i++) {
				head = new Pair(get(i), head);
			}
			return head;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new EmptyList();
	}

	@Override
	public IntegerList extend(IntegerList other) {
		IntegerList extendedList = getClone();
		for (int i = 0; i < other.length(); i++) {
			try {
				extendedList = extendedList.append(other.get(i));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return extendedList;
	}

	@Override
	public int min() {
		int min = rest.min();
		return element < min ? element : min;
	}

	@Override
	public boolean isSorted() {
		try {
			if (element < rest.get(0))
				return rest.isSorted();
		} catch (Exception e) { // more exceptions!
			// last item
			return true;
		}
		return false;
	}

	@Override
	public Pair getClone() {
		int length = length();
		try {
			Pair head = new Pair(get(length - 1));
			for (int i = length - 2; i >= 0; i--) {
				head = new Pair(get(i), head);
			}
			return head;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}