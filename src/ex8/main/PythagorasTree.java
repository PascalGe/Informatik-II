package ex8.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PythagorasTree {

	public static void drawRotatedRect(Graphics2D g, Vector pos, Vector up, int a, int height) {
		// TODO 8.3.b) Draw a rectangle that is rotated according to the up vector
		g.setColor(new Color(255 - 1, 0, 1));

		// TODO Eckpunkte berechnen (pos = mitte?)
		Vector lowerLeft = pos.added(up.rotated(135).scaled(a / 2));
		Vector lowerRight = pos.added(up.rotated(-135).scaled(a / 2));
		Vector upperLeft = pos.added(up.rotated(45).scaled(a / 2));
		Vector upperRight = pos.added(up.rotated(-45).scaled(a / 2));

		int[] xPoints = { (int) lowerLeft.x, (int) upperLeft.x, (int) upperRight.x, (int) lowerRight.x };
		int[] yPoints = { height - (int) lowerLeft.y, height - (int) upperLeft.y, height - (int) upperRight.y,
				height - (int) lowerRight.y };
		g.fillPolygon(xPoints, yPoints, 4);
	}

	public static void drawSegment(Graphics2D g, Vector pos, Vector up, int a, int height) {
		// TODO 8.3.c) Compute positions and orientations for the branches
		// continue recursively for every new branch
		if (a < 2) {
			return;
		}
		drawRotatedRect(g, pos, up, a, height);

		Vector vLeft = up.scaled(a / 2).rotated(90).added(up.scaled(a));
		Vector vRight = up.scaled(a / 2).rotated(-90).added(up.scaled(a));

//		g.drawRect((int) vLeft.added(pos).x, (int) vLeft.added(pos).y, 10, 10);

		drawRotatedRect(g, pos.added(vLeft), up.rotated(45), a / 2, height);
		drawRotatedRect(g, pos.added(vRight), up.rotated(-45), a / 2, height);

//		drawSegment(g, vLeft, up.rotated(45), a / 2, height);
//		drawSegment(g, vRight, up.rotated(-45), a / 2, height);
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