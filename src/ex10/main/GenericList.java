package ex10.main;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class GenericList<E> implements List<E> {

	int firstEmptyIdx;
	E[] data;

	public GenericList() {
		// TODO constructor initializes data with 4 elements!
		// Hint: You can create a generic array as Object array, which is casted to type
		// E:
		// E[] array = (E[]) new Object[sizeOfArray];
		firstEmptyIdx = 0;
		data = (E[]) new Object[4];
	}

	// Check the documentation of the List interface for descriptions of the
	// following Methods:

	@Override
	public boolean add(E arg0) {
		// TODO
		return false;
	}

	@Override
	public void add(int arg0, E arg1) {
		// TODO
	}

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		// TODO
		return false;
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends E> arg1) {
		// Bonus (2 Punkte)
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		// TODO
	}

	@Override
	public boolean contains(Object arg0) {
		// TODO
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO
		return false;
	}

	@Override
	public E get(int arg0) {
		// TODO
		return null;
	}

	@Override
	public int indexOf(Object arg0) {
		// TODO
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO
		return false;
	}

	@Override
	public int lastIndexOf(Object arg0) {
		// TODO
		return 0;
	}

	@Override
	public boolean remove(Object arg0) {
		// TODO
		return false;
	}

	@Override
	public E remove(int arg0) {
		// TODO
		return null;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// Bonus
		throw new UnsupportedOperationException();
	}

	@Override
	public E set(int arg0, E arg1) {
		// TODO
		return null;
	}

	@Override
	public int size() {
		// TODO
		return 0;
	}

	@Override
	public List<E> subList(int arg0, int arg1) {
		// TODO
		// Hint you can use subList method from Array.asList(data)
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO
		return null;
	}

	////////////////////// You dont have to touch the following code ///////////////

	/**
	 * The GenericListIterator implements the ListIterator interface, which allows
	 * us to use Java Iterators, and all their features on the Generic list. For
	 * example you can use the foreach loop to iterate over all elements in a
	 * GenericList: for (E elt : list) See the printList Method in Generics.java for
	 * an example.
	 *
	 * @param <E> Type of the GnereicList elements.
	 */
	@SuppressWarnings("hiding")
	class GenericListIterator<E> implements ListIterator<E> {

		private GenericList<E> list;
		private int position;

		/**
		 * Creates a GenericListIterator at position 0 in the list.
		 * 
		 * @param list The list which is iterated.
		 */
		GenericListIterator(GenericList<E> list) {
			this.list = list;
			position = 0;
		}

		/**
		 * Creates a GenericListIterator at the given position in the list.
		 * 
		 * @param list     The list which is iterated.
		 * @param position Start position of the iterator.
		 */
		GenericListIterator(GenericList<E> list, int position) {
			this.list = list;
			this.position = position;
		}

		// For descriptions of the overwritten methods see the JavaDocs of the
		// @see(ListIterator) interface.
		@Override
		public void add(E arg0) {
			list.add(arg0);
		}

		@Override
		public boolean hasNext() {
			return position < list.size();
		}

		@Override
		public boolean hasPrevious() {
			return position > 0;
		}

		@Override
		public E next() {
			if (position >= list.size()) {
				throw new NoSuchElementException();
			}
			E e = list.get(position);
			position += 1;
			return e;
		}

		@Override
		public int nextIndex() {
			return position;
		}

		@Override
		public E previous() {
			if (position <= 0) {
				throw new NoSuchElementException();
			}
			position -= 1;
			E e = list.get(position);
			return e;
		}

		@Override
		public int previousIndex() {
			return position - 1;
		}

		@Override
		public void remove() {
			list.remove(position);
		}

		@Override
		public void set(E arg0) {
			list.set(position, arg0);
		}

	}

	// The Iterator related methods just return a GenericListIterator of this list.
	// For detailed explanation see the documentation of the List and Iterator
	// interface.
	@Override
	public Iterator<E> iterator() {
		return listIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		return new GenericListIterator<E>(this);
	}

	@Override
	public ListIterator<E> listIterator(int arg0) {
		if (arg0 < 0 || arg0 >= firstEmptyIdx) {
			throw new IndexOutOfBoundsException(arg0);
		}
		return new GenericListIterator<E>(this, arg0);
	}

}
