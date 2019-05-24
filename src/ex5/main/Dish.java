package ex5.main;

/*
 * Scratch:
 * ########
 * 
 * Fields:
 * - name: String		Names are Strings
 * - dirty: float		Values between 0.0 and 1.0,  precision high enough
 * - size: int			Just integer value sizes
 * 
 * Methods:
 * - Dish(String, float)		Constructor
 * - printStatus(): void		output console
 * - clean(float): void			dirty is float, side effects
 * - determineSize(): void		sets size out of name
 * - getName(): String			Getter
 * - getDirty(): float			Getter
 * - getSize(): int				Getter
 */

/**
 * 
 * @author Pascal Gepperth (4005085)
 *
 */
public class Dish {

	// Define java constants
	private static final int SIZE_CUTLERY = 1;
	private static final int SIZE_PLATE = 2;
	private static final int SIZE_CUP = 2;
	private static final int SIZE_POT = 5;

	// Fields
	private String name; // name of the dish --> String
	private float dirty; // dirtiness within 0% and 100% --> float
	private int size; // size of the dish --> int

	/**
	 * Creates a new dish with name and dirtiness.
	 * 
	 * @param name  - the name of the dish.
	 * @param dirty - the dirtiness of the dish.
	 */
	public Dish(String name, float dirty) {
		this.name = name;
		determineSize();
		if (dirty < 0) {
			this.dirty = 0;
		} else if (dirty > 1) {
			this.dirty = 1;
		} else {
			this.dirty = dirty;
		}
	}

	/**
	 * Prints the current status of the dish, which is dirty or clean.
	 */
	public void printStatus() {
		if (dirty > .1f) {
			System.out.println(name + " still dirty");
		} else {
			System.out.println(name + " is clean");
		}
	}

	/**
	 * Cleans the dish.
	 * 
	 * @param value - the value that the dirtiness is to be reduced by.
	 */
	public void clean(float value) {
		if (value > dirty) {
			dirty = 0;
		} else {
			dirty -= value;
		}
	}

	/**
	 * Sets the size of the dish using its name.
	 */
	private void determineSize() {
		switch (name) {
		case "Cutlery":
			size = SIZE_CUTLERY;
			break;
		case "Plate":
			size = SIZE_PLATE;
			break;
		case "Cup":
			size = SIZE_CUP;
			break;
		case "Pot":
			size = SIZE_POT;
			break;
		default:
			break;
		}
	}

	/**
	 * Getter
	 * 
	 * @return the name of the dish.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter
	 * 
	 * @return the dirtiness of the dish.
	 */
	public float getDirty() {
		return dirty;
	}

	/**
	 * Getter
	 * 
	 * @return the size of the dish.
	 */
	public int getSize() {
		return size;
	}
}