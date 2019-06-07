package ex7.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * 
 * @author Pascal Gepperth (4005085)
 *
 */
public abstract class LinkEntity {

	/**
	 * Location of the entity.
	 */
	protected Vector pos;

	/**
	 * Color of the entity.
	 */
	protected Color col;

	/**
	 * Next entity.
	 */
	protected LinkEntity next;

	/**
	 * Creates a new single entity at (x,y).
	 * 
	 * @param x - coordinate.
	 * @param y - coordinate.
	 */
	public LinkEntity(int x, int y) {
		pos = new Vector(x, y);
		setNext(this);
	}

	/**
	 * Creates a new single entity at (x,y) and sets the color.
	 * 
	 * @param x   - coordinate
	 * @param y   - coordinate
	 * @param col - color
	 */
	public LinkEntity(int x, int y, Color col) {
		this(x, y);
		this.col = col;
	}

	/**
	 * @return position of the entity.
	 */
	public Vector getPos() {
		return pos;
	}

	/**
	 * Draws the entity.
	 * 
	 * @param g        - the graphics object.
	 * @param area     - the area to paint.
	 * @param tileSize - the size of a tile.
	 */
	public void draw(Graphics2D g, Rectangle area, int tileSize) {
		g.setColor(col);
		g.fillRect(area.x + pos.x * tileSize, area.y + pos.y * tileSize, tileSize, tileSize);
		if (!isLast()) {
			next.draw(g, area, tileSize);
		}
	}

	/**
	 * @return true, if there is no next entity.
	 */
	public boolean isLast() {
		return next == this;
	}

	/**
	 * @return number of entities linked to the current one and it's next + 1.
	 */
	public int getLength() {
		if (!isLast()) {
			return 1 + getNext().getLength();
		}
		return 1;
	}

	/**
	 * Sets the next entity.
	 * 
	 * @param next - next entity.
	 * @return the entity that's set as next.
	 */
	public LinkEntity setNext(LinkEntity next) {
		this.next = next;
		return this.next;
	}

	/**
	 * @return the next linked entity.
	 */
	public LinkEntity getNext() {
		return next;
	}

	/**
	 * Checks the entity and it's following occupying (x,y).
	 * 
	 * @param x - coordinate.
	 * @param y - coordinate.
	 * @return true, if the entity or one of the following occupies (x,y).
	 */
	public boolean isOccupied(int x, int y) {
		return isOccupied(x, y, false);
	}

	/**
	 * Checks the entity and it's following occupying (x,y) and may clips the
	 * occupied one.
	 * 
	 * @param x    - coordinate.
	 * @param y    - coordinate.
	 * @param clip - true, if clipping is enabled.
	 * @return true, if the entity or one of the following occupies (x,y).
	 */
	public boolean isOccupied(int x, int y, boolean clip) {
		return isOccupied(x, y, clip, this);
	}

	/**
	 * Checks the entity and it's following occupying (x,y) and may clips the
	 * occupied one by replacing the previous' next.
	 * 
	 * @param x    - coordinate.
	 * @param y    - coordinate.
	 * @param clip - true, if clipping is enabled.
	 * @param prev - the previous linked entity.
	 * @return true, if the entity or one of the following occupies (x,y).
	 */
	private boolean isOccupied(int x, int y, boolean clip, LinkEntity prev) {
		if (x == pos.x && y == pos.y) {
			if (clip) {
				prev.setNext(prev);
			}
			return true;
		}
		if (isLast()) {
			return false;
		}
		return next.isOccupied(x, y, clip, this);
	}
}