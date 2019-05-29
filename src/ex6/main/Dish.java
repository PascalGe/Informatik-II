package ex6.main;

public class Dish {

	public int size;
	public float dirtReduction = .3f;
	public float dirty;

	public Dish(float dirty) {
		this.dirty = dirty;
	}

	public void printStatus() {
		if (dirty > .1) {
			System.out.println(getClass().getSimpleName() + "still dirty");
		} else {
			System.out.println(getClass().getSimpleName() + "is clean");
		}

	}

	public void clean() {
		if (dirty < dirtReduction) {
			dirty = 0;
		} else {
			dirty -= dirtReduction;
		}
	}

}
