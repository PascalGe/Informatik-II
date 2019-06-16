package ex7.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * A class to handle labels, goals, scores itself and a method to draw them.
 * 
 * @author Informatik II
 */
public class GameScore {

	private String[] labels;
	private int[] scores;
	private int[] goals;

	private static Font myBaseFont = new Font("Times", Font.BOLD, 16);

	/**
	 * Constructor
	 * 
	 * @param labels - the text labels.
	 * @param scores - the current scores.
	 * @param goals  - the goals to reach.
	 */
	public GameScore(String[] labels, int[] scores, int[] goals) {
		this.labels = labels;
		this.scores = scores;
		this.goals = goals;
	}

	/**
	 * Paints the current GameScore.
	 * 
	 * @param g        - the 2DGraphics object.
	 * @param area     - the area to paint in.
	 * @param tileSize - the tile size.
	 */
	public void paintScore(Graphics2D g, Rectangle area, int tileSize) {
		Font temp = g.getFont();
		g.setFont(myBaseFont);

		int firstHeight = area.y + area.height / 2;
		int heightSteps = (int) (1.2 * myBaseFont.getSize());
		if (labels.length > 1) { // more than one line of scores to paint
			firstHeight -= (int) (((labels.length - 1) / 2.) * heightSteps);
		}

		g.setColor(Color.BLACK);
		for (int i = 0; i < labels.length; i++) {
			g.drawString(labels[i] + ": " + scores[i] + " / " + goals[i], area.x + area.width / tileSize - tileSize / 2,
					firstHeight + i * heightSteps);
		}
		g.setFont(temp);
	}
}