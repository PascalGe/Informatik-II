package ex8.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @author Pascal Gepperth (4005085)
 *
 */
public class PythagorasTree {

	/**
	 * Draws a rotated rectangle.
	 * 
	 * @param g      - the Graphics2D object.
	 * @param pos    - the center of the rectangle.
	 * @param up     - a vector that points in the "up" direction (with length 1).
	 * @param a      - the rectangle's width.
	 * @param height - image's height.
	 */
	public static void drawRotatedRect(Graphics2D g, Vector pos, Vector up, int a, int height) {
		// set beautiful color
		int colorA = a;
		int colorB = 0;
		if (colorA > 255) {
			colorB = (colorA - 255) % 256;
			colorA = 255;
		}
		g.setColor(new Color(255 - colorA, colorB, colorA));

		// calculate edges
		Vector lowerLeft = pos.added(up.rotated(3 * Math.PI / 4).scaled(a / Math.sqrt(2)));
		Vector lowerRight = pos.added(up.rotated(-3 * Math.PI / 4).scaled(a / Math.sqrt(2)));
		Vector upperLeft = pos.added(up.rotated(Math.PI / 4).scaled(a / Math.sqrt(2)));
		Vector upperRight = pos.added(up.rotated(-Math.PI / 4).scaled(a / Math.sqrt(2)));

		// draw rectangle
		int[] xPoints = { (int) lowerLeft.x, (int) upperLeft.x, (int) upperRight.x, (int) lowerRight.x };
		int[] yPoints = { height - (int) lowerLeft.y, height - (int) upperLeft.y, height - (int) upperRight.y,
				height - (int) lowerRight.y };
		g.fillPolygon(xPoints, yPoints, 4);
	}

	/**
	 * Draws the rectangle and all it's children.
	 * 
	 * @param g      - the Graphics2D object.
	 * @param pos    - the center of the rectangle.
	 * @param up     - a vector that points in the "up" direction (with length 1).
	 * @param a      - the rectangle's start width.
	 * @param height - image's height.
	 */
	public static void drawSegment(Graphics2D g, Vector pos, Vector up, int a, int height) {
		if (a < 2) {
			return;
		}

		// draw current
		drawRotatedRect(g, pos, up, a, height);

		// Compute shift for the branches
		Vector shiftLeft = up.scaled(a / 2).rotated(Math.PI / 2).added(up.scaled(a));
		Vector shiftRight = up.scaled(a / 2).rotated(-Math.PI / 2).added(up.scaled(a));

		drawSegment(g, pos.added(shiftLeft), up.rotated(Math.PI / 4), (int) (a / Math.sqrt(2)), height);
		drawSegment(g, pos.added(shiftRight), up.rotated(-Math.PI / 4), (int) (a / Math.sqrt(2)), height);
	}

	// Create the Pythagoras-Tree image and save it as PNG file.
	public static void createPythagorasTreeImage(int startSize) throws IOException {
		// Creation of the image object
		int height = 5 * startSize;
		int width = 8 * startSize;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// Create a Graphics2D object from the image and set a white background
		Graphics2D g = image.createGraphics();
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, width, height);

		// Initial position and orientation of the first segment
		Vector startPos = new Vector(width / 2, startSize);
		Vector up = new Vector(0, 1);

		// Start the recursion.
		drawSegment(g, startPos, up, startSize, height);

		// Save the image as PNG
		String OS = System.getProperty("os.name").toLowerCase(); // different for win and unix
		String filePath = System.getProperty("user.dir") + (OS.indexOf("win") >= 0 ? "\\" : "/") + "pythagorasTree.png";
		System.out.println("Writing pythagoras-tree image to: " + filePath);
		ImageIO.write(image, "png", new File(filePath));
	}

	public static void main(String[] args) throws IOException {

		createPythagorasTreeImage(256);

	}
}