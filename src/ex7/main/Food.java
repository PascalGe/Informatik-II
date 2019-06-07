package ex7.main;

/**
 * Food objects are very simple and do not implement much functionality. The
 * foods value is determined randomly (with a maximum defined in the game) and
 * defines the color.
 * 
 * @author Pascal Gepperth (4005085)
 */
public class Food extends LinkEntity {

	private int value;

	/**
	 * Creates a new Food at (x,y) with a random value.
	 * 
	 * @param x - coordinate.
	 * @param y - coordinate.
	 */
	public Food(int x, int y) {
		super(x, y);
		value = SnakeGame.random.nextInt(SnakeGame.maxFoodValue) + 1;
		if (value == SnakeGame.maxFoodValue) {
			col = SnakeGame.foodUltra;
		} else if (value >= SnakeGame.maxFoodValue * .8) {
			col = SnakeGame.foodSuper;
		} else {
			col = SnakeGame.foodBasic;
		}
	}

	/**
	 * @return value of the food.
	 */
	public int getValue() {
		return value;
	}
}