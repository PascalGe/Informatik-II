package ex7.main;

import java.awt.Rectangle;

/**
 * The barrier surrounds the game area and spawns new elements of increasing
 * number (based on the level) within the game area. The first constructor sets
 * the base block at (-1,-1) and initializes the rest of the field with the
 * other private constructor.
 * 
 * @author Informatik II, Pascal Gepperth (4005085)
 */
public class Barrier extends LinkEntity {

	private Rectangle borderArea;
	private Barrier next;

	/**
	 * Creates the barrier surrounding the game area and some random barriers based
	 * on the level.
	 * 
	 * @param gameArea - the game area that is to surround.
	 * @param level    - the current level.
	 */
	public Barrier(Rectangle gameArea, int level) {
		super(-1, -1);
		col = SnakeGame.arenaBarrier;
		next = (Barrier) setNext(this);

		borderArea = new Rectangle(gameArea.x / MainFrame.tileSize, gameArea.y / MainFrame.tileSize,
				gameArea.width / MainFrame.tileSize, gameArea.height / MainFrame.tileSize);

		for (int y = 0; y < borderArea.height; y++) {
			for (int x = 0; x < borderArea.width; x++) {
				if (y == 0 || x == 0 || y == (borderArea.height - 1) || x == (borderArea.width - 1)) {
					extend(x - 1, y - 1);
				}
			}
		}
		// spawn random barrier based on the level at free place
		int x, y;
		for (int i = 0; i < SnakeGame.barrierPerLevel * level; i++) {
			do {
				x = SnakeGame.random.nextInt(borderArea.width - 1);
				y = SnakeGame.random.nextInt(borderArea.height - 1);
			} while (isOccupied(x, y));
			extend(x, y);
		}
	}

	/**
	 * Creates a new single barrier at (x,y).
	 * 
	 * @param x - coordinate.
	 * @param y - coordinate.
	 */
	private Barrier(int x, int y) {
		super(x, y);
		col = SnakeGame.arenaBarrier;
		setNext(this);
	}

	/**
	 * Extends the barrier by a new single barrier at (x,y).
	 * 
	 * @param x - coordinate.
	 * @param y - coordinate.
	 */
	public void extend(int x, int y) {
		if (isLast()) {
			next = (Barrier) setNext(new Barrier(x, y));
		} else {
			next = (Barrier) getNext();
			next.extend(x, y);
		}
	}
}