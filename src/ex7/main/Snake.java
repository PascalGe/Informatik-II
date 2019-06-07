package ex7.main;

/**
 * The snake has two constructors, the first one (public) is for the head only,
 * the other one for the rest of the body (private). As the snake moves, it can
 * eat and extend its length, run into itself and lose length or run into a
 * barrier and die.
 * 
 * @author Informatik II, Pascal Gepperth (4005085)
 */
public class Snake extends LinkEntity {

	private boolean alive; // TODO unused!!

	/**
	 * Number of lives.
	 */
	private int lives;

	/**
	 * Number of steps waiting before move.
	 */
	private int wait;
	private Vector dir;

	/**
	 * Creates a new snake at (x,y) with given length and number of lives.
	 * 
	 * @param x           - coordinate
	 * @param y           - coordinate
	 * @param lives       - number of lives
	 * @param startLength - length of the snake
	 */
	public Snake(int x, int y, int lives, int startLength) {
		super(x, y, SnakeGame.snakeHead);

		alive = true;
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
		super(pos.x, pos.y, SnakeGame.snakeBody);

		alive = true;
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
		dir = newDir;
	}

	/**
	 * 
	 * @return true, if there are still lives left.
	 */
	public boolean isAlive() {
		alive = lives > 0;
		return alive;
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
	 * @param food    - food that can be eaten.
	 * @param barrier - barrier that can be collided with.
	 * @return true, if snake ate some food.
	 */
	public boolean move(Food food, Barrier barrier) {
		if (barrier.isOccupied(pos.x + dir.x, pos.y + dir.y)) {
			lives = 0;
			alive = false;
			return false;
		}
		move();
		if (eat(food)) {
			return true;
		} else if (selfCollision(pos) && lives == 0) {
			alive = false;
		}
		return false;
	}

	/**
	 * Moves snake part, if not waiting. Otherwise decreases waiting.
	 */
	private void move() {
		if (wait > 0) {
			wait--;
		} else {
			pos.x += dir.x;
			pos.y += dir.y;
		}
		// move next body part
		if (!isLast()) {
			((Snake) getNext()).move();
		}
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
}