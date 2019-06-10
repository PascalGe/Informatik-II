package ex7.main;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class PowerUp extends LinkEntity {

	/**
	 * Number of steps the PowerUp has an effect.
	 */
	private int timeOfEffect;
	private boolean used;

	public PowerUp(int x, int y) {
		super(x, y, SnakeGame.powerUpColor, SnakeGame.powerUpImage);
		this.timeOfEffect = SnakeGame.basicPowerUpEffectTime;
		used = false;
	}

	public int getTimeOfEffect() {
		return timeOfEffect;
	}

	public void setUsed() {
		used = true;
	}

	@Override
	public void draw(Graphics2D g, Rectangle area, int tileSize, boolean uhd) {
		if (!used) {
			super.draw(g, area, tileSize, uhd);
		}
	}

	public void reduceTimeOfEffect() {
		timeOfEffect--;
	}

	public boolean isUsed() {
		return used;
	}
}
