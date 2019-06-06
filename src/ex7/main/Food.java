package ex7.main;

import java.util.Random;

/**
 * Food objects are very simple and do not implement much functionality. The
 * foods value is determined randomly (with a maximum defined in the game) and
 * defines the color.
 */
public class Food extends LinkEntity {

	private static Random r = new Random();

//	private Vector pos;
	private int value;

	public Food(int x, int y) {
		super(x, y);
		value = r.nextInt(SnakeGame.maxFoodValue) + 1;
		if (value == SnakeGame.maxFoodValue) {
			col = SnakeGame.foodUltra;
		} else if (value >= SnakeGame.maxFoodValue * .8) {
			col = SnakeGame.foodSuper;
		} else {
			col = SnakeGame.foodBasic;
		}
	}

	public int getValue() {
		return value;
	}
}