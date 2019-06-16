package ex7.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * 
 * @author Pascal Gepperth (4005085)
 *
 */
public abstract class LinkEntity {

	protected static final int ROTATION_UP = 1;
	protected static final int ROTATION_LEFT = 2;
	protected static final int ROTATION_RIGHT = 3;
	protected static final int ROTATION_DOWN = 4;

	/**
	 * Rotation of the entity.
	 */
	private int rotation;

	/**
	 * Location of the entity.
	 */
	protected Vector pos;

	/**
	 * Color of the entity.
	 */
	protected Color col;

	/**
	 * Image of the entity.
	 */
	protected BufferedImage img;

	/**
	 * Transformation to rotate image.
	 */
	protected AffineTransform at;

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
		this(x, y, null, null);
	}

	/**
	 * Creates a new single entity at (x,y) and sets the color.
	 * 
	 * @param x   - coordinate
	 * @param y   - coordinate
	 * @param col - color
	 */
	public LinkEntity(int x, int y, Color col) {
		this(x, y, col, null);
	}

	public LinkEntity(int x, int y, BufferedImage img) {
		this(x, y, null, img);
	}

	public LinkEntity(int x, int y, Color col, BufferedImage img) {
		pos = new Vector(x, y);
		setNext(this);
		this.col = col;
		this.img = img;
		at = new AffineTransform();
	}

	/**
	 * @return position of the entity.
	 */
	public Vector getPos() {
		return pos;
	}

	/**
	 * Setter.
	 * 
	 * @param rotation - new rotation.
	 */
	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	/**
	 * Getter.
	 * 
	 * @return the current rotation.
	 */
	public int getRotation() {
		return rotation;
	}

	/**
	 * Draws the entity.
	 * 
	 * @param g        - the graphics object.
	 * @param area     - the area to paint.
	 * @param tileSize - the size of a tile.
	 */
	private void drawBasic(Graphics2D g, Rectangle area, int tileSize) {
		g.setColor(col);
		g.fillRect(area.x + pos.x * tileSize, area.y + pos.y * tileSize, tileSize, tileSize);
		if (!isLast()) {
			next.drawBasic(g, area, tileSize);
		}
	}

	public void draw(Graphics2D g, Rectangle area, int tileSize) {
		draw(g, area, tileSize, false);
	}

	public void draw(Graphics2D g, Rectangle area, int tileSize, boolean uhd) {
		if (uhd && img != null) {
			drawAdvanced(g, area, tileSize);
		} else {
			drawBasic(g, area, tileSize);
		}
	}

	/**
	 * draws the entity by it's UHD image.
	 * 
	 * @param g        - the 2D graphics object.
	 * @param area     - the area to draw in.
	 * @param tileSize - the tile size.
	 */
	private void drawAdvanced(Graphics2D g, Rectangle area, int tileSize) {
		adjustImage(area, tileSize);
		g.drawImage(img, at, null);
		if (!isLast()) {
			next.drawAdvanced(g, area, tileSize);
		}
	}

	/**
	 * Adjusts the UHD image
	 * 
	 * @param area     - area the image is to place in.
	 * @param tileSize - the tile size.
	 */
	private void adjustImage(Rectangle area, int tileSize) {
		int theta;
		switch (rotation) {
		case ROTATION_LEFT:
			theta = 270;
			break;
		case ROTATION_RIGHT:
			theta = 90;
			break;
		case ROTATION_DOWN:
			theta = 180;
			break;
		default:
			theta = 0;
			break;
		}
		// move image to location
		at.setToTranslation(area.x + pos.x * tileSize, area.y + pos.y * tileSize);
		// center image at location
		at.translate((tileSize - img.getWidth()) / 2, (tileSize - img.getHeight()) / 2);
		// rotate image
		at.rotate(Math.toRadians(theta), img.getWidth() / 2, img.getHeight() / 2);
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
	 * Checks the entity and it's following occupying the location the vecor is
	 * pointing to.
	 * 
	 * @param vec - coordinates.
	 * @return true, if the entity or one of the following occupies (x,y).
	 */
	public boolean isOccupied(Vector vec) {
		return isOccupied(vec.x, vec.y, false);
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