package ex7.main;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * The snake has two constructors, the first one (public) is for the head only,
 * the other one for the rest of the body (private). As the snake moves, it can
 * eat and extend its length, run into itself and lose length or run into a
 * barrier and die.
 * 
 * @author Informatik II, Pascal Gepperth (4005085)
 */
public class Snake extends LinkEntity {

	/**
	 * Number of lives.
	 */
	private int lives;

	/**
	 * Number of steps waiting before move.
	 */
	private int wait;
	private Vector dir;

	private boolean ignoreCollision = false;

	/**
	 * Creates a new snake at (x,y) with given length and number of lives.
	 * 
	 * @param x           - coordinate
	 * @param y           - coordinate
	 * @param lives       - number of lives
	 * @param startLength - length of the snake
	 */
	public Snake(int x, int y, int lives, int startLength) {
		super(x, y, SnakeGame.snakeHeadColor, SnakeGame.snakeHeadImage);

		this.lives = lives;
		wait = 0;

		dir = SnakeGame.initialSnakeDir;

		extend(startLength - 1);
	}

	/**
	 * Creates a snake body.
	 * 
	 * @param pos - location of the body part.
	 * @param dir - direction to move.
	 * @param n   - number of steps waiting before move.
	 */
	private Snake(Vector pos, Vector dir, int n) {
		super(pos.x, pos.y, SnakeGame.snakeBodyColor, SnakeGame.snakeBodyImage);

		wait = n;

		this.dir = dir;
	}

	/**
	 * Sets the direction for the next movement for every part of the snake.
	 * 
	 * @param newDir - new direction.
	 */
	public void changeDirection(Vector newDir) {
		// if part of snake has next, pass current direction (follow this)
		if (!isLast()) {
			((Snake) getNext()).changeDirection(dir);
		}
		if (newDir.x == 1) {
			setRotation(ROTATION_RIGHT);
		} else if (newDir.x == -1) {
			setRotation(ROTATION_LEFT);
		} else if (newDir.y == 1) {
			setRotation(ROTATION_DOWN);
		} else {
			setRotation(ROTATION_UP);
		}
		dir = newDir;
	}

	/**
	 * 
	 * @return true, if there are still lives left.
	 */
	public boolean isAlive() {
		return lives > 0;
	}

	/**
	 * 
	 * @return number of lives left.
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * Extends the snake.
	 * 
	 * @param n - number of body parts to extend.
	 */
	private void extend(int n) {
		if (n <= 0) {
			return;
		}
		if (!isLast()) {
			// goto next an extend
			((Snake) getNext()).extend(n);
		} else {
			// set wait one higher than it's previous
			((Snake) setNext(new Snake(pos, dir, wait + 1))).extend(n - 1);
		}
	}

	/**
	 * Checks snake for occupies food. Extends snake by the food value and increases
	 * lives by one, if snake ate ultraFood.
	 * 
	 * @param food - food, that can be eaten.
	 * @return true, if snake ate food.
	 */
	private boolean eat(Food food) {

		boolean eaten = food.isOccupied(pos.x, pos.y);
		if (eaten) {
			extend(food.getValue());
			if (food.getValue() == SnakeGame.maxFoodValue) {
				lives++;
			}
		}
		return eaten;
	}

	/**
	 * Checks for colliding with barrier. If no collision, moves the snake and calls
	 * eat() and selfCollison().
	 * 
	 * @param food            - food that can be eaten.
	 * @param barriersArround - barrier that can be collided with.
	 * @return true, if snake ate some food.
	 */
	public boolean move(Food food, Barrier barriersArround, Barrier barriersInside, int rows, int cols) {
		if (!ignoreCollision && barriersArround.isOccupied(pos.x + dir.x, pos.y + dir.y)) {
			lives = 0;
			return false;
		} else if (!ignoreCollision && barriersInside.isOccupied(getNextPos(rows, cols))) {
			lives = 0;
			return false;
		}
		move(rows, cols);
		if (eat(food)) {
			return true;
		}
		if (!ignoreCollision) {
			selfCollision(pos);
		}
		return false;
	}

	/**
	 * Moves snake part, if not waiting. Otherwise decreases waiting.
	 */
	private void move(int rows, int cols) {
		if (wait > 0) {
			wait--;
		} else {
			pos = getNextPos(rows, cols);
		}
		// move next body part
		if (!isLast()) {
			((Snake) getNext()).move(rows, cols);
		}
	}

	/**
	 * Returns the next position referring to the borders.
	 * 
	 * @param rows - number of rows inside the gameArea.
	 * @param cols - number of columns inside the gameArea.
	 * @return the next position as Vector.
	 */
	private Vector getNextPos(int rows, int cols) {
		Vector newPos = new Vector(pos.x + dir.x, pos.y + dir.y);
		if (newPos.x < 0) {
			newPos.x = cols - 1;
		} else if (newPos.x > cols - 1) {
			newPos.x = 0;
		}
		if (newPos.y < 0) {
			newPos.y = rows - 1;
		} else if (newPos.y > rows - 1) {
			newPos.y = 0;
		}
		return newPos;
	}

	/**
	 * Checks snake for selfCollision and clips at collision point.
	 * 
	 * @param headPos - head position of snake.
	 * @return true, if snake collides with itself.
	 */
	private boolean selfCollision(Vector headPos) {

		// checks for self collision and clips the snake if necessary
		if (getNext().isOccupied(headPos.x, headPos.y, true)) {
			lives--;
			return true;
		}
		return false;
	}

	/**
	 * Sets the snake's ignore collision state.
	 * 
	 * @param ignoreCollision - new state.
	 */
	public void setIgnoreCollision(boolean ignoreCollision) {
		this.ignoreCollision = ignoreCollision;
	}

	@Override
	public void draw(Graphics2D g, Rectangle area, int tileSize, boolean uhd) {
		if (ignoreCollision) {
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f));
			super.draw(g, area, tileSize, uhd);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		} else {
			super.draw(g, area, tileSize, uhd);
		}
	}
}