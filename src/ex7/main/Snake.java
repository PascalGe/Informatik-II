package ex7.main;

import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * The snake has two constructors, the first one (public) is for the head only,
 * the other one for the rest of the body (private). As the snake moves, it can
 * eat and extend its length, run into itself and lose length or run into a
 * barrier and die.
 */
public class Snake extends LinkEntity {

//	protected Vector pos;
//	protected Color col;
	private boolean alive; // TODO unused!!
	private int lives;
	private int wait;
	private Vector dir;
//	private Snake next;

	public Snake(int _x, int _y, int _lives, int _startLength) {
		super(_x, _y, SnakeGame.snakeHead);
//		pos = new Vector(_x, _y);
//		setNext(this);

		alive = true;
		lives = _lives;
		wait = 0;

		dir = SnakeGame.initialSnakeDir;
//		col = SnakeGame.snakeHead;

		extend(_startLength - 1);
	}

	private Snake(Vector _pos, Vector _dir, int n) {
//		pos = new Vector(_pos.x, _pos.y);
		super(_pos.x, _pos.y, SnakeGame.snakeBody);
//		setNext(this);

		alive = true;
		wait = n;

		dir = _dir;
//		col = SnakeGame.snakeBody;
	}

	public void changeDirection(Vector newDir) {
		// TODO: pass own direction to next and update own dir
		if (!isLast()) {
			getNext().changeDirection(dir);
		}
		dir = newDir;
	}

	public Snake setNext(Snake _next) {
//		next = _next;
//		return next;
		return (Snake) super.setNext(_next);
	}

	public Snake getNext() {
//		return next;
		return (Snake) super.getNext();
	}

	public int getLength() {
		if (!isLast()) {
			return 1 + getNext().getLength();
		}
		return 1;
	}

	public boolean isAlive() {
		alive = lives > 0;
		return alive;
	}

	public int getLives() {
		return lives;
	}

//	public boolean isLast() {
//		return next == this;
//	}

	private void extend(int n) {
		System.out.println(n);
		if (n <= 0) {
			return;
		}
		// TODO: create new object and append it to the end of the chain
		if (!isLast()) {
			getNext().extend(n);
		} else {
			setNext(new Snake(pos, dir, wait + 1)).extend(n - 1);
		}
	}

	private boolean eat(Food food) {

		// TODO: check food position and update the snake appropriately
		boolean eaten = food.isOccupied(pos.x, pos.y);
		if (eaten) {
			extend(food.getValue());
			if (food.getValue() == SnakeGame.maxFoodValue) {
				lives++;
			}
		}
		return eaten;
	}

	public boolean move(Food food, Barrier barrier) {
		if (barrier.isOccupied(pos.x + dir.x, pos.y + dir.y)) {
			lives = 0;
			alive = false;
			return false;
		}
		move();
		// TODO: watch out for food or barriers
		if (eat(food)) {
			return true;
		} else if (selfCollision(pos) && --lives == 0) {
			alive = false;
		}
		return false;
	}

	private void move() {
		if (wait > 0) {
			wait -= 1;
		} else {
			pos.x += dir.x;
			pos.y += dir.y;
		}
		// TODO: recursion
		if (!isLast()) {
			getNext().move();
		}
	}

	private boolean selfCollision(Vector headPos) {

		// TODO: check for self collision and clip the snake if necessary
		if (getNext().isOccupied(headPos.x, headPos.y, true)) {
			lives--;
		}
		return false;
	}

	public void draw(Graphics2D g, Rectangle snakeArea, int tileSize) {
		g.setColor(col);
		g.fillRect(snakeArea.x + pos.x * tileSize, snakeArea.y + pos.y * tileSize, tileSize, tileSize);
		if (!isLast()) {
			next.draw(g, snakeArea, tileSize);
		}
	}
}