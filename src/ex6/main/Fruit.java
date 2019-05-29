package ex6.main;

public class Fruit extends Food {

	private boolean hasPeel;

	public Fruit(String _name, int _kcal, boolean _hasPeel) {
		super(_name, _kcal);
		hasPeel = _hasPeel;
		if (!hasPeel) {
			edible = true;
		}
		// TODO: complete constructor
	}

	public int prepare(int weight) {
		if (hasPeel) {
			hasPeel = false;
			edible = true;
			weight *= .9;
		}
		// TODO: to make the fruit edible,
		// peel it if necessary and reduce the weight
		return weight;
	}

}
