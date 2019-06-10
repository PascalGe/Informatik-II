package ex7.main;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
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
	private boolean unused;
	private float opacity = 1f;

	/**
	 * Creates the barrier surrounding the game area and some random barriers based
	 * on the level.
	 * 
	 * @param gameArea - the game area that is to surround.
	 * @param level    - the current level.
	 */
	public Barrier(Rectangle gameArea, int level) {
		this(gameArea);

		extendSurrounding();
		extendRandom(level);
	}

	private Barrier(Rectangle gameArea) {
		super(-2, -2, SnakeGame.arenaBarrier, SnakeGame.arenaBarrierImage);

		borderArea = new Rectangle(gameArea.x / MainFrame.tileSize, gameArea.y / MainFrame.tileSize,
				gameArea.width / MainFrame.tileSize, gameArea.height / MainFrame.tileSize);
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
		img = SnakeGame.arenaBarrierImage;
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
			setNext(new Barrier(x, y));
		} else {
			((Barrier) getNext()).extend(x, y);
		}
	}

	public void setUnused(boolean unused) {
		this.unused = unused;
	}

	@Override
	public void draw(Graphics2D g, Rectangle area, int tileSize, boolean uhd) {
		if (!unused) {
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
			super.draw(g, area, tileSize, uhd);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}
	}

	@Override
	public boolean isOccupied(int x, int y) {
		if (!unused) {
			return super.isOccupied(x, y);
		}
		return false;
	}

	@Override
	public boolean isOccupied(Vector vec) {
		if (!unused) {
			return super.isOccupied(vec);
		}
		return false;
	}

	private void extendSurrounding() {
		// surrounding barrier
		for (int y = 0; y < borderArea.height; y++) {
			for (int x = 0; x < borderArea.width; x++) {
				if (y == 0 || x == 0 || y == (borderArea.height - 1) || x == (borderArea.width - 1)) {
					extend(x - 1, y - 1);
				}
			}
		}
	}

	private void extendRandom(int count) {
		// spawn random barrier based on the level at free place
		int x, y;
		for (int i = 0; i < SnakeGame.barrierPerLevel * count; i++) {
			do {
				x = SnakeGame.random.nextInt(borderArea.width - 2);
				y = SnakeGame.random.nextInt(borderArea.height - 2);
			} while (isOccupied(x, y));
			extend(x, y);
		}
	}

	public static Barrier createSurrounding(Rectangle gameArea) {
		Barrier surr = new Barrier(gameArea);
		surr.extendSurrounding();
		return (Barrier) surr.getNext();
	}

	public static Barrier createRandom(Rectangle gameArea, int count) {
		Barrier surr = new Barrier(gameArea);
		surr.extendRandom(count);
		return (Barrier) surr.getNext();
	}

	public void setOpacity(float opacity) {
		if (opacity < 0 || opacity > 1) {
			throw new IllegalArgumentException("Value must be between 0 and 1.");
		}
		this.opacity = opacity;
	}
}