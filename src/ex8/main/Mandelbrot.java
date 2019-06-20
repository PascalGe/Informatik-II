package ex8.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @author Pascal Gepperth (4005085)
 *
 */
public class Mandelbrot {

	/**
	 * Checks the complex number c of being an element of the mandelbrot set
	 * recursively.
	 * 
	 * @param c               - complex number to check for being element of the
	 *                        set.
	 * @param z               - last element in sequence.
	 * @param n               - number of element in sequence.
	 * @param recursion_limit - max recursion depth.
	 * @return 0 if c is element of the set, otherwise the number of recursions till
	 *         exit condition.
	 */
	public static int mandelbrot(ComplexNumber c, ComplexNumber z, int n, int recursion_limit) {
		// if max recursion reached, it belongs to M
		if (n > recursion_limit) {
			return 0;
		}

		// get next number in sequence
		ComplexNumber z_n = ComplexNumber.add(ComplexNumber.square(z), c);

		// exit condition, it does not belong to M.
		if (z_n.abs() > 2) {
			return 1;
		}

		// no exit condition
		// fetch result
		int result = mandelbrot(c, z_n, ++n, recursion_limit);

		// return 0 if c belongs to M, else increase needed steps.
		return result == 0 ? 0 : ++result;
	}

	// Create the Mandelbrot image, fill it and save it as PNG file.
	public static void createMandelbrotImage(int tileSize, int maxRecurse) throws IOException {
		int height = 2 * tileSize;
		int width = 3 * tileSize;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		ComplexNumber z0 = new ComplexNumber(0, 0);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// Construct a complex number from the pixel coordinates
				float xPos = (x + 0.5f - 2 * tileSize) / tileSize;
				float yPos = (y + 0.5f - tileSize) / tileSize;
				ComplexNumber c = new ComplexNumber(xPos, yPos);

				// Check the Mandelbrot condition for this complex number
				int mb = mandelbrot(c, z0, 0, maxRecurse);

				// Translate the result to number in a reasonable range and use it as color.
				double mbl = mb > 0 ? Math.log(mb) / Math.log(maxRecurse) : 0;
				image.setRGB(x, y, (int) (mbl * 255));
			}
		}

		// Save the image as PNG
		String OS = System.getProperty("os.name").toLowerCase(); // different for win and unix
		String filePath = System.getProperty("user.dir") + (OS.indexOf("win") >= 0 ? "\\" : "/") + "mandelbrot.png";
		System.out.println("Writing mandelbrot image to: " + filePath);
		ImageIO.write(image, "png", new File(filePath));
	}

	public static void main(String[] args) throws IOException {
		createMandelbrotImage(500, 2 ^ 24);
	}
}