package ex7.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * The snake has two constructors, the first one (public) is for the head only,
 * the other one for the rest of the body (private). As the snake moves, it can
 * eat and extend its length, run into itself and lose length or run into a
 * barrier and die.
 */
public class Snake {

    protected Vector pos;
    protected Color col;
    private boolean alive;
    private int lives;
    private int wait;
    private Vector dir;
    private Snake next;

    public Snake(int _x, int _y, int _lives, int _startLength) {
	pos = new Vector(_x, _y);
	setNext(this);

	alive = true;
	lives = _lives;
	wait = 0;

	dir = SnakeGame.initialSnakeDir;
	col = SnakeGame.snakeHead;

	extend(_startLength - 1);
    }

    private Snake(Vector _pos, Vector _dir, int n) {
	pos = new Vector(_pos.x, _pos.y);
	setNext(this);

	alive = true;
	wait = n;

	dir = _dir;
	col = SnakeGame.snakeBody;
    }

    public void changeDirection(Vector newDir) {

	// TODO: pass own direction to next and update own dir

    }

    public Snake setNext(Snake _next) {
	next = _next;
	return next;
    }

    public Snake getNext() {
	return next;
    }

    public int getLength() {
	if (!isLast()) {
	    return 1 + next.getLength();
	}
	return 1;
    }

    public boolean isOccupied(int x, int y) {

	// TODO: check if x,y is occupied - (also for all following objects)

	return false;
    }

    public boolean isAlive() {
	alive = lives > 0;
	return alive;
    }

    public int getLives() {
	return lives;
    }

    public boolean isLast() {
	return next == this;
    }

    private void extend(int n) {

	// TODO: create new object and append it to the end of the chain

    }

    private boolean eat(Food food) {

	// TODO: check food position and update the snake appropriately

	return false;
    }

    public boolean move(Food food, Barrier barrier) {
	move();

	// TODO: watch out for food or barriers

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

    }

    private boolean selfCollision(Vector headPos) {

	// TODO: check for self collision and clip the snake if necessary

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
