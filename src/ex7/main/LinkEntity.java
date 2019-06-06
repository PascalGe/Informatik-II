package ex7.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class LinkEntity {

	protected Vector pos;
	protected Color col;
	protected LinkEntity next;

	public LinkEntity(int x, int y) {
		pos = new Vector(x, y);
		setNext(this);
	}

	public LinkEntity(int x, int y, Color col) {
		this(x, y);
		this.col = col;
	}

	public void draw(Graphics2D g, Rectangle snakeArea, int tileSize) {
		g.setColor(col);
		g.fillRect(snakeArea.x + pos.x * tileSize, snakeArea.y + pos.y * tileSize, tileSize, tileSize);
		if (!isLast()) {
			next.draw(g, snakeArea, tileSize);
		}
	}

	public boolean isLast() {
		return next == this;
	}

	protected LinkEntity setNext(LinkEntity next) {
		this.next = next;
		return this.next;
	}

	public LinkEntity getNext() {
		return next;
	}

	// TODO: check if x,y is occupied - (also for all following objects)
	public boolean isOccupied(int x, int y) {
		return isOccupied(x, y, false);
	}

	public boolean isOccupied(int x, int y, boolean clip) {
		return isOccupied(x, y, clip, this);
	}

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